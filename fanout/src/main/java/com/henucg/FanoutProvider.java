package com.henucg;

import com.henucg.util.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.jupiter.api.Test;

/**
 * @Author chenge
 * @Description desc
 * @Date 2021/9/13 19:35
 */
public class FanoutProvider {

    @Test
    public void sendMessage() throws Exception {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        // 绑定交换机
        // 参数1：交换机名称，没有自动创建
        // 参数1：交换机类型，fanout：广播类型
        channel.exchangeDeclare("logs","fanout");

        // 发送消息
        channel.basicPublish("logs","",null,"fanout message".getBytes());

        RabbitMQUtils.closeConnectionAndChannel(connection,channel);
    }
}
