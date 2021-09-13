package com.henucg.consumer;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author chenge
 * @Description desc
 * @Date 2021/9/13 21:45
 */
@Component
public class RoutingConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, //创建临时队列
                    exchange = @Exchange(value = "logs",type = "direct"),    //交换机
                    key = {"info","error","warn"}
            )
    })
    public void consumeMessage(String message){}
}
