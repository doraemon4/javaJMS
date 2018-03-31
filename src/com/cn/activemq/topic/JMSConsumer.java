package com.cn.activemq.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSConsumer {
	private static final String USERNAME=ActiveMQConnection.DEFAULT_USER;
	private static final String PASSWORD=ActiveMQConnection.DEFAULT_PASSWORD;
	private static final String BROKERURL=ActiveMQConnection.DEFAULT_BROKER_URL;
	private static final int SENDNUM=10;
	
	public static void main(String[] args) {
		ConnectionFactory connectionFactory;  //连接工厂
		Connection connection = null;
		Session session; //发送和接收消息的线程
		Destination destination;//消息的目的地
		MessageConsumer messageConsumer;//消息消费者
		
		//实例化连接工厂
		connectionFactory=new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKERURL);
		try {
			//获取连接
			connection=connectionFactory.createConnection();
			connection.start();//启动连接
			session=connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			destination=session.createTopic("firstTopic");//创建消息队列
			messageConsumer=session.createConsumer(destination);//创建消息消费者
			
			messageConsumer.setMessageListener(new   MessageListener() {
				
				@Override
				public void onMessage(Message message) {
					TextMessage textMessage=(TextMessage)message;
					try {
						System.out.println("订阅到的消息:"+textMessage.getText());
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			});
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}
}
