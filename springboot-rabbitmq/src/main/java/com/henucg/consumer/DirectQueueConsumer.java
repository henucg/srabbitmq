package com.henucg.consumer;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author chenge
 * @Description 直连模式消费者
 * @Date 2021/9/13 21:24
 */
@Component
@RabbitListener(queuesToDeclare = @Queue("direct_queue"))
public class DirectQueueConsumer {

    @RabbitHandler
    public void consumer(String message){
        System.out.println("消费消息" + message);
    }
}
