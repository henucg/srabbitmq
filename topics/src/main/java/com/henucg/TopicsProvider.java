package com.henucg;

import com.henucg.util.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.jupiter.api.Test;

/**
 * @Author chenge
 * @Description desc
 * @Date 2021/9/13 20:26
 */
public class TopicsProvider {

    @Test
    public void sendMessage() throws Exception {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        // 绑定交换机
        // 参数1：交换机名称
        // 参数2：交换机类型，topic：带统配符的Routing模式
        channel.exchangeDeclare("topics","topic");

        String routingKey = "user.save";
        // 发送消息
        channel.basicPublish("topics",routingKey,null,("这里是topics动态路由模型，routingKey:" + routingKey).getBytes());

        RabbitMQUtils.closeConnectionAndChannel(connection,channel);
    }
}
