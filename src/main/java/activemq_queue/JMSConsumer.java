package main.java.activemq_queue;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消费者（定时接收消息）
 * @author
 */
public class JMSConsumer {
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER; //默认的用户名
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD; //默认的密码
    /*failover://tcp://localhost:61616*/
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL; //默认的连接地址

    public static void main(String[] args) {
        ConnectionFactory connectionFactory; //连接工厂
        Connection connection = null; //连接
        Session session; //会话 接受或者发送消息的线程
        Destination destination; //消息的目的地
        MessageConsumer messageConsumer;//消息的消费者

        //实例化连接工厂
        connectionFactory = new ActiveMQConnectionFactory(JMSConsumer.USERNAME, JMSConsumer.PASSWORD, JMSConsumer.BROKEURL);
        try {
            connection = connectionFactory.createConnection();//创建连接工厂获取连接
            connection.start();
            session = connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("FirstQueue1");//创建连接的消息队列
            messageConsumer = session.createConsumer(destination);
            while (true){
                TextMessage textMessage = (TextMessage)messageConsumer.receive(100000);//100秒接收一次
                if(textMessage != null){
                    System.out.println("收到的消息："+textMessage.getText());
                }else {
                    break;
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }


    }


}
