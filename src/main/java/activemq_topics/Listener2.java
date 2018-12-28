package main.java.activemq_topics;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 消息监听-订阅者一
 * @author
 */
public class Listener2 implements MessageListener {


    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("订阅者二收到的消息："+((TextMessage)message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }


    }
}
