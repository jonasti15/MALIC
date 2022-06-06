package RabbitMQ;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Suscriber2 {
	final static String EXCHANGE_NAME = "ciclocompleto";
	final static String DLX_NAME = "MiDeadLetterExchange";
	final static String QUEUE_NAME = "numeros";
	
	ConnectionFactory factory;
	final int MAX_INTENTOS = 3;
	
	public Suscriber2() {
		factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setUsername("guest");
		factory.setPassword("guest");
	}

	public void suscribe() {
		
		Channel channel = null;
		try (Connection connection = factory.newConnection()){
			
			channel = connection.createChannel();
			channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
			channel.exchangeDeclare(DLX_NAME, "fanout");
			
			
			boolean durable = false;
			boolean exclusive = false;
			boolean autodelete = false;
			Map<String,Object> arguments = new HashMap<>();
			arguments.put("x-dead-letter-exchange", DLX_NAME);

			channel.queueDeclare(QUEUE_NAME, durable, exclusive,autodelete,arguments);
			channel.queueBind (QUEUE_NAME,EXCHANGE_NAME,"");
			
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
		ConcurrentMap<String, Integer> contadores;
		
		public MiConsumer(Channel channel) {
			super(channel);
			contadores = new ConcurrentHashMap<>();
		}

		@Override
		public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
				throws IOException {
			String message = new String(body, "UTF-8");
			try {
				int valor = Integer.parseInt(message);
				boolean multiple = false;
				this.getChannel().basicAck(envelope.getDeliveryTag(), multiple);
				
			}catch (NumberFormatException e) {
				Integer intentos = contadores.get(message);
				if (intentos == null) intentos = 1;
				System.out.println("ERROR: No se ha podido procesar "+message+" intentos: "+intentos);
				
				if (intentos  == MAX_INTENTOS) {
					boolean reprocesar = false;
					boolean multiple = false;
					contadores.remove(message);
					//this.getChannel().basicNack(envelope.getDeliveryTag(), multiple, reprocesar);
					this.getChannel().basicReject(envelope.getDeliveryTag(), reprocesar);
				}else {
					boolean reprocesar = true;
					boolean multiple = false;
					contadores.put(message, ++intentos);
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
		Suscriber2 suscriber = new Suscriber2();
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
