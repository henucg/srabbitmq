package com.henucg;

import com.henucg.util.RabbitMQUtils;
import com.rabbitmq.client.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @Author chenge
 * @Description desc
 * @Date 2021/9/13 18:58
 */
public class WorkQueueConsumer2 {

    @Test
    public void consumeMessage() throws Exception {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("work_queue",false,false,false,null);

        // 平均分配消费（队列默认会平均发送消息给多个消费者，这样可能导致消息丢失）
        channel.basicConsume("work_queue",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                // super.handleDelivery(consumerTag, envelope, properties, body);
                try {
                    Thread.sleep(1000);
                } catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println("消费者2：" + new String(body));
            }
        });
    }
}
