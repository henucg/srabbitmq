package com.henucg;

import com.henucg.util.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import org.junit.jupiter.api.Test;

/**
 * @Author chenge
 * @Description desc
 * @Date 2021/9/13 18:58
 */
public class WorkQueueProvider {

    @Test
    public void sendMessage() throws Exception {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("work_queue",false,false,false,null);

        // 生产100条消息
        for (int i=0;i<100;i++){
            channel.basicPublish("","work_queue", MessageProperties.PERSISTENT_TEXT_PLAIN,("hello world" + i).getBytes());
        }

        RabbitMQUtils.closeConnectionAndChannel(connection,channel);
    }
}
