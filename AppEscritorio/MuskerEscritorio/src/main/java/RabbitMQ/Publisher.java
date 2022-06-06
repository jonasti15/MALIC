package RabbitMQ;


import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Publisher {
	final static String EXCHANGE_NAME = "ciclocompleto";
	final static String DLX_NAME = "MiDeadLetterExchange";
	final static String DLX_QUEUE = "MiDeadLetterQueue";
	
	final static int NUM_VALORES = 20;
	ConnectionFactory factory;
	Random generador;
	
	public Publisher() {
		factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setUsername("guest");
		factory.setPassword("guest");
		//puerto 5672 o 5673 para TLS
		generador = new Random();
	}
	public void enviarMensaje() {
		try(Connection connection = factory.newConnection()){
			
			Channel channel = connection.createChannel();
			channel.exchangeDeclare(DLX_NAME, "fanout");
			channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
			boolean durable = false;
			boolean exclusive = false;
			boolean autodelete = true;
			Map<String,Object> arguments = null;
			String message;
			channel.queueDeclare(DLX_QUEUE, durable, exclusive,autodelete,arguments);
			channel.queueBind(DLX_QUEUE, DLX_NAME, "");
			Consumer consumerDeadLetter  = new ConsumerDeadLetter(channel);
			channel.basicConsume(DLX_QUEUE, true, consumerDeadLetter);
			
			
			
			BasicProperties properties = null;
			for (int i = 0; i<NUM_VALORES; i++) {
				if ( generador.nextBoolean()) {
					message = String.valueOf(i);
				}else {
					 message = String.valueOf(i)+"$";
				}
				channel.basicPublish(EXCHANGE_NAME,"", properties, message.getBytes());
				System.out.println("Enviado: " + message );
			}
			synchronized (this) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			channel.close ();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public class ConsumerDeadLetter extends DefaultConsumer {

		public ConsumerDeadLetter(Channel channel) {
			super(channel);
			
		}

		@Override
		public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
				throws IOException {
			String message = new String (body,"UTF-8");
			System.out.println("valor rechazado: " + message);
		}
		
	}
	public synchronized void stop() {
		this.notify();
	}
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		Publisher publisher = new Publisher();
		System.out.println(" Esperando mensaje. Pulsa return para terminar");
		Thread hiloEspera = new Thread(()-> {
			teclado.nextLine();
			publisher.stop();
		});
		hiloEspera.start();
		publisher.enviarMensaje();
		teclado.close();
	}

}
