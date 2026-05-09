package com.mq.activemq.basic.web;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.jms.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author shenlx
 * @description
 * @date 2026/5/9 17:21
 */
@SpringBootTest
public class ApplicationTest {

    // ActiveMQ连接地址（替换为你的服务器地址）
    private static final String BROKER_URL = "tcp://192.168.1.252:61616";
    // 要消费的Topic名称
    private static final String TOPIC_NAME = "TagData";
    // 归档文件路径（可替换为数据库写入逻辑）
    private static final String ARCHIVE_FILE = "consumed_messages.log";

    public static void main(String[] args) throws JMSException, IOException {
        // 1. 创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin", BROKER_URL);
        Connection connection = connectionFactory.createConnection();
        // 持久化订阅需要设置客户端ID（非必须，仅持久化订阅时需配置）
        connection.setClientID("CONSUMER_ARCHIVE_01");
        connection.start();

        // 2. 创建会话（事务可选，这里设为false，签收模式为自动）
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 3. 创建Topic和消费者（持久化订阅用createDurableSubscriber，普通订阅用createSubscriber）
        Topic topic = session.createTopic(TOPIC_NAME);
        // 持久化订阅（需保证ClientID唯一）：适合需要保留未消费消息的场景
        MessageConsumer consumer = session.createDurableSubscriber(topic, "ARCHIVE_SUBSCRIPTION");
        // 普通订阅（非持久化）：consumer = session.createConsumer(topic);

        // 4. 监听并消费消息，同时归档内容
        consumer.setMessageListener(message -> {
            try {
                // 处理文本消息（可扩展为MapMessage、ObjectMessage等）
                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    String msgContent = textMessage.getText();
                    String msgId = message.getJMSMessageID();
                    Date consumeTime = new Date();

                    // 打印消费日志
                    System.out.printf("消费消息：ID=%s，内容=%s，时间=%s%n",
                            msgId, msgContent, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(consumeTime));

                    // 归档到本地文件（生产环境建议替换为数据库/ES等）
                    archiveMessage(msgId, msgContent, consumeTime);
                }
            } catch (JMSException | IOException e) {
                e.printStackTrace();
            }
        });

        // 保持消费者运行（防止程序退出）
        System.out.println("消费者已启动，开始消费并归档消息...");
        System.in.read();

        // 关闭资源（实际生产中通常不主动关闭）
        consumer.close();
        session.close();
        connection.close();
    }

    /**
     * 归档已消费的消息内容到文件
     */
    private static void archiveMessage(String msgId, String content, Date consumeTime) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARCHIVE_FILE, true))) {
            writer.printf("[%s] 消息ID：%s，内容：%s%n",
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(consumeTime),
                    msgId, content);
        }
    }
}
