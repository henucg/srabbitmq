package com.henucg.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @Author chenge
 * @Description desc
 * @Date 2021/9/13 18:49
 */
public class RabbitMQUtils {

    private static ConnectionFactory connectionFactory ;

    static {
        // 创建连接MQ的连接工厂
        connectionFactory = new ConnectionFactory();
        // 设置连接地址
        connectionFactory.setHost("127.0.0.1");
        // 设置端口号
        connectionFactory.setPort(5672);
        // 设置连接的虚拟机
        connectionFactory.setVirtualHost("cg_virtual_host");
        // 访问虚拟机的用户名和密码
        connectionFactory.setUsername("cg");
        connectionFactory.setPassword("cg");
        // 设置超时时间
        connectionFactory.setConnectionTimeout(100000);
    }

    public static Connection getConnection(){
        try {
            return connectionFactory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnectionAndChannel(Connection connection, Channel channel){
        try {
            if(channel != null){
                channel.close();
            }

            if(connection != null){
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
