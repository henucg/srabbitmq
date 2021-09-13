package com.henucg;

import com.henucg.util.RabbitMQUtils;
import com.rabbitmq.client.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @Author chenge
 * @Description desc
 * @Date 2021/9/13 19:51
 */
public class RoutingConsumer2 {

    @Test
    public void consumeMessage() throws Exception {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        // 通道绑定交换机
        channel.exchangeDeclare("logs_direct","fanout");

        // 每个消费者创建一个临时队列
        String queue = channel.queueDeclare().getQueue();

        // 绑定交换机和队列（只消费routing key为warn的消息），可以绑定多个
        // channel.queueBind(queue,"logs_direct","info");
        channel.queueBind(queue,"logs_direct","warn");
        // channel.queueBind(queue,"logs_direct","error");

        // 消费消息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                // super.handleDelivery(consumerTag, envelope, properties, body);
                System.out.println("消费者2：" + new String(body));
            }
        });
    }
}
