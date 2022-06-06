package RabbitMQ;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Suscriber {
	final static String QUEUE_NAME = "numeros";
	ConnectionFactory factory;
	final int MAX_INTENTOS = 3;
	
	public Suscriber() {
		factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setUsername("guest");
		factory.setPassword("guest");
	}

	public void suscribe() {
		
		Channel channel = null;
		try (Connection connection = factory.newConnection()){
			
			channel = connection.createChannel();
			boolean durable = false;
			boolean exclusive = false;
			boolean autodelete = false;
			Map<String,Object> arguments = null;
			
			channel.queueDeclare(QUEUE_NAME, durable, exclusive,autodelete,arguments);
			channel.basicQos(5);
			MiConsumer consumer = new MiConsumer(channel);
			boolean autoack = false;
			String tag = channel.basicConsume(QUEUE_NAME, autoack, consumer);
			
			synchronized (this) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			channel.basicCancel(tag);
			channel.close();
			
		} catch (IOException | TimeoutException e) {
			
			e.printStackTrace();
		}
		
	}
	public synchronized void stop() {
		this.notify();
	}
	public class MiConsumer extends DefaultConsumer{
		int contador = 1;
		public MiConsumer(Channel channel) {
			super(channel);
			
		}

		@Override
		public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
				throws IOException {
			String message = new String(body, "UTF-8");
			try {
				int valor = Integer.parseInt(message);
				System.out.println((esPrimo(valor))?valor + " es primo": valor + " no es primo");
				boolean multiple = false;
				this.getChannel().basicAck(envelope.getDeliveryTag(), multiple);
				
			}catch (NumberFormatException e) {
				System.out.println("ERROR: No se ha podido procesar "+message+" intentos: "+contador);
				
				if (contador == MAX_INTENTOS) {
					boolean reprocesar = false;
					boolean multiple = false;
					contador = 1;
					//this.getChannel().basicNack(envelope.getDeliveryTag(), multiple, reprocesar);
					this.getChannel().basicReject(envelope.getDeliveryTag(), reprocesar);
				}else {
					boolean reprocesar = true;
					boolean multiple = false;
					contador++;
					this.getChannel().basicNack(envelope.getDeliveryTag(), multiple, reprocesar);
				}
			}
			
			
		}
		private boolean esPrimo(int valor) {
			
			for (int i = 2; i<(valor/2); i++ ) {
				if (valor%i == 0) return false;
			}
			return true;
		}
		
	}
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		Suscriber suscriber = new Suscriber();
		System.out.println(" Esperando mensaje. Pulsa return para terminar");
		Thread hiloEspera = new Thread(()-> {
			teclado.nextLine();
			suscriber.stop();
		});
		hiloEspera.start();
		suscriber.suscribe();
		teclado.close();
		
	}

}
