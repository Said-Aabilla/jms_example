package org.example;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class MessageSender {
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    private static String subject = "JCG_QUEUE";

    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue(subject);
        MessageProducer producer = session.createProducer(destination);
        CustomMessage customMessage = new CustomMessage("id", "userId", "senderName", "content", "12/12/2022");
        ObjectMessage objectMessage = session.createObjectMessage();
        objectMessage.setObject(customMessage);

        producer.send(objectMessage);
        connection.close();
    }
}
