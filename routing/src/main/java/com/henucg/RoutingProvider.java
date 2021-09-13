package com.henucg;

import com.henucg.util.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.jupiter.api.Test;

/**
 * @Author chenge
 * @Description desc
 * @Date 2021/9/13 20:05
 */
public class RoutingProvider {

    @Test
    public void sendMessage() throws Exception {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        // 生命交换机
        // 参数1：交换机名称，不存在自动生成
        // 参数2：交换机模式，direct：路由模式（消息发送到每个连接该交换机队列上）
        channel.exchangeDeclare("logs_direct","direct");

        String routingKey = "info";
        // 发送消息
        channel.basicPublish("log_direct",routingKey,null,("这是direct模式下基于routing key:"+routingKey+"发送的消息").getBytes());

        RabbitMQUtils.closeConnectionAndChannel(connection,channel);
    }
}
