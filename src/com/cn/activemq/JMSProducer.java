package com.cn.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消息生产者
 * @author Administrator
 *
 */
public class JMSProducer {
	private static final String USERNAME=ActiveMQConnection.DEFAULT_USER;
	private static final String PASSWORD=ActiveMQConnection.DEFAULT_PASSWORD;
	private static final String BROKERURL=ActiveMQConnection.DEFAULT_BROKER_URL;
	private static final int SENDNUM=10;
	
	public static void main(String[] args) {
		ConnectionFactory connectionFactory;  //连接工厂
		Connection connection = null;
		Session session; //发送和接收消息的线程
		Destination destination;//消息的目的地
		MessageProducer messageProducer;//消息生产者
		
		//实例化连接工厂
		connectionFactory=new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKERURL);
		try {
			//获取连接
			connection=connectionFactory.createConnection();
			connection.start();//启动连接
			session=connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			destination=session.createQueue("firstQueue");//创建消息队列
			messageProducer=session.createProducer(destination);//创建消息生产者
			sendMessage(session, messageProducer);
			session.commit();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(connection!=null){
				try {
					connection.close();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void sendMessage(Session session,MessageProducer messageProducer) throws Exception{
		for(int i=0;i<SENDNUM;i++){
			TextMessage textMessage=session.createTextMessage("ActiveMQ发送消息:"+i);
			System.out.println("发送消息："+textMessage.getText());
			messageProducer.send(textMessage);
		}
	}
}
