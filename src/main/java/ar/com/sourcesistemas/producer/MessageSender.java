package ar.com.sourcesistemas.producer;


import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MessageSender {

    //URL of the JMS server. DEFAULT_BROKER_URL will just mean that JMS server is on localhost
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    // default broker URL is : tcp://localhost:61616"
    private static String subject = "JCG_QUEUE"; // Queue Name.You can create any/many queue names as per your requirement.

    private Connection connection;
    private Session session;
    private MessageProducer producer;


    public MessageSender() throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        connection = connectionFactory.createConnection();
        connection.start();

        session = connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue(subject);
        producer = session.createProducer(destination);


    }

    public boolean sendTextMessage(String messageToSend) {
        TextMessage messageBuilder = null;
        try {
            messageBuilder = session
                    .createTextMessage(messageToSend);
            producer.send(messageBuilder);
        } catch (JMSException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    public boolean closeConnection() {
        try {
            System.out.println("closing activemq connection.");
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}