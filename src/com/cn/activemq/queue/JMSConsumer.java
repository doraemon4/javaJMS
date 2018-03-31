package com.cn.activemq.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSConsumer {
	private static final String USERNAME=ActiveMQConnection.DEFAULT_USER;
	private static final String PASSWORD=ActiveMQConnection.DEFAULT_PASSWORD;
	private static final String BROKERURL=ActiveMQConnection.DEFAULT_BROKER_URL;
	
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
			destination=session.createQueue("firstQueue");//创建消息队列
			messageConsumer=session.createConsumer(destination);//创建消息消费者
			
			while (true) {
				TextMessage textMessage=(TextMessage) messageConsumer.receive(100000);
				if(textMessage!=null){
					System.out.println("接收到的消息是："+textMessage.getText());
				}else{
					break;
				}
			}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
