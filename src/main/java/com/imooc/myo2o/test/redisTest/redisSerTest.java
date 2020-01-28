package com.imooc.myo2o.test.redisTest;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.repository.query.RedisOperationChain;

import java.util.List;

public class redisSerTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("redis-config.xml");
        RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);
//        //设值
//        redisTemplate.opsForValue().set("key1","value1");
//        redisTemplate.opsForValue().set("key2","value2");
//        //取值
//        String value1 = (String) redisTemplate.opsForValue().get("key1");
//        System.out.println(value1);


         //测试事务
//        SessionCallback callback = (SessionCallback) (RedisOperations ops) -> {
//            String value =null;
//            ops.multi();
//            ops.boundValueOps("key1").set("value1");
//            value = (String) ops.boundValueOps("key1").get();
//
//            System.out.println(value);
//            List list = ops.exec();
//
//            value = (String) redisTemplate.opsForValue().get("key1");
//            System.out.println(value);
//            return value;
//        };
//
//        redisTemplate.execute(callback);
    }
}
