package com.henucg;

import com.henucg.util.RabbitMQUtils;
import com.rabbitmq.client.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @Author chenge
 * @Description desc
 * @Date 2021/9/13 16:56
 */
public class DirectConsumer {

    @Test
    public void consumeMessage() throws Exception {
        // 获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        // 获取连接通道
        Channel channel = connection.createChannel();

        // 通道绑定对应的消息队列
        // 参数1:队列名称，如果不存在自动创建
        // 参数2:队列特性是否持久化
        // 参数3:是否独占队列
        // 参数4:是否消费完成之后自动删除
        // 参数5:额外附加参数
        channel.queueDeclare("cg_queue",false,false,false,null);

        // 发送消息
        // 参数1：交换机名称
        // 参数2：开启消息自动确认机制
        // 参数3：消费时的回调接口
        channel.basicConsume("cg_queue",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String var1, Envelope var2, AMQP.BasicProperties var3, byte[] var4) throws IOException{
                System.out.println("消费消息：" + new String(var4));
            }
        });

        // 不建议关闭，不关闭一直会消费队列里面的消息
        // RabbitMQUtils.closeConnectionAndChannel(connection,channel);
    }
}
