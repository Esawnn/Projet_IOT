package mqtt;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.commons.lang3.SerializationUtils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import business.PublisherBusiness;
import entity.Publisher;

@WebListener
	public class Consumer implements ServletContextListener {
		
		private final static String QUEUE_NAME = "hello";

		public Consumer() throws Exception {
			// Create a factory of connections to the MOM
			ConnectionFactory factory = new ConnectionFactory();
			// Specify the address of the MOM server
			factory.setHost("localhost");
			// Create a new connection to the MOM
			Connection connection = factory.newConnection();
			// Create a new channel within this connection
			// to consume and to produce messages
			Channel channel = connection.createChannel();
			// Declare the queue to be used with the parameters:
			// durable:false, exclusive: false, autodelete:false,
			// additional-arguments:null
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			// now we will be ready to receive messages
			System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
			DeliverCallback deliverCallback = (consumerTag, delivery) -> {
				//byte[] byteArray = delivery.getBody();
			
				//Publisher publisher = (Publisher) SerializationUtils.deserialize(byteArray);
				
				String message = new String(delivery.getBody(), "UTF-8");
				System.out.println(" [x] Received '" + message + "'");
				PublisherBusiness publisherBusiness = new PublisherBusiness();
				publisherBusiness.setData(message);
				
			};
			channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
			});
}
		
		public static Object deserialize(byte[] byteArray) throws IOException, ClassNotFoundException {
			ByteArrayInputStream in = new ByteArrayInputStream(byteArray);
			ObjectInputStream is = new ObjectInputStream(in);
			return is.readObject();
		}
	}
