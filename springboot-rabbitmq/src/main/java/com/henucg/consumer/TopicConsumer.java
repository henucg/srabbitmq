package com.henucg.consumer;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author chenge
 * @Description desc
 * @Date 2021/9/13 21:39
 */
@Component
public class TopicConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, //不置顶队列名称，创建临时队列
                    exchange = @Exchange(value = "topics",type = "topic"),    //绑定交换机
                    key = {"user.*","user.#"}
            )
    })
    public void consumeMessage(String message){
        System.out.println(message);
    }
}
