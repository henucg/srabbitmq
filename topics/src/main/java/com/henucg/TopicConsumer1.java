package com.henucg;

import com.henucg.util.RabbitMQUtils;
import com.rabbitmq.client.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @Author chenge
 * @Description desc
 * @Date 2021/9/13 20:31
 */
public class TopicConsumer1 {

    @Test
    public void consumeMessage() throws Exception {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        // 通道绑定交换机
        channel.exchangeDeclare("topics","topic");

        // 每个消费者创建一个临时队列
        String queue = channel.queueDeclare().getQueue();
        // 绑定队列和交换机，通配符形式的routing key
        channel.queueBind(queue,"topic","user.*");

        // 消费消息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                // super.handleDelivery(consumerTag, envelope, properties, body);
                System.out.println("消费者1：" + new String(body));
            }
        });
    }
}
