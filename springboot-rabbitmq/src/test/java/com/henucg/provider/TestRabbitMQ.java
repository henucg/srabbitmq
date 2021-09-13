package com.henucg.provider;

import com.henucg.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author chenge
 * @Description desc
 * @Date 2021/9/13 21:14
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class TestRabbitMQ {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * direct模式
     */
    @Test
    public void testDirect(){
        rabbitTemplate.convertAndSend("direct_queue","hello world");
    }

    /**
     * work-queue模式
     */
    @Test
    public void testWorkQueue(){
        for (int i=0;i<10;i++) {
            rabbitTemplate.convertAndSend("work_queue", "hello world");
        }
    }

    /**
     * 广播模式
     */
    @Test
    public void testFanout(){
        rabbitTemplate.convertAndSend("logs","", "hello world");
    }

    /**
     * 路由模式
     */
    @Test
    public void testRouting(){
        rabbitTemplate.convertAndSend("logs","info", "hello world");
    }

    /**
     * 订阅模式
     */
    @Test
    public void testTopics(){
        rabbitTemplate.convertAndSend("topics","user.save", "hello world");
    }
}
