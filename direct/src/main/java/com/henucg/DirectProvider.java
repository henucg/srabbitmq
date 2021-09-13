package com.henucg;

import com.henucg.util.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import org.junit.jupiter.api.Test;

/**
 * @Author chenge
 * @Description desc
 * @Date 2021/9/13 16:56
 */
public class DirectProvider {

    @Test
    public void sendMessage() throws Exception {
        // 获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        // 获取连接通道
        Channel channel = connection.createChannel();

        // 通道绑定对应的消息队列
        // 参数1:队列名称，如果不存在自动创建
        // 参数2:队列是否持久化
        // 参数3:是否独占队列
        // 参数4:是否消费完成之后自动删除队列
        // 参数5:额外附加参数
        channel.queueDeclare("direct_queue",false,false,false,null);

        // 发送消息
        // 参数1：交换机名称
        // 参数2：队列名称
        // 参数3：传递消息额外设置
        //  MessageProperties.PERSISTENT_TEXT_PLAIN：消息持久化
        // 参数4：具体的消息内容
        channel.basicPublish("","direct_queue", MessageProperties.PERSISTENT_TEXT_PLAIN,"hello world".getBytes());

        RabbitMQUtils.closeConnectionAndChannel(connection,channel);
    }
}
