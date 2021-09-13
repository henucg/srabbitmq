package com.henucg.consumer;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author chenge
 * @Description work-queue模式消费者
 * @Date 2021/9/13 21:32
 */
@Component
public class WorkQueueConsumer {

    /**
     * 平均消费
     */
    @RabbitListener(queuesToDeclare = @Queue("work_queue"))
    public void consume1(String message){
        System.out.println("消费者1："+message);
    }

    /**
     * 平均消费
     */
    @RabbitListener(queuesToDeclare = @Queue("work_queue"))
    public void consume2(String message){
        System.out.println("消费者2："+message);
    }
}
