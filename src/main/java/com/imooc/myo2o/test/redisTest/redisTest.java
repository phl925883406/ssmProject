package com.imooc.myo2o.test.redisTest;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

import java.util.List;

public class redisTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);


        try {
            int i = 0;
            long a = System.currentTimeMillis();
            while (true) {
                long b = System.currentTimeMillis();
                if (b - a >= 1000) {
                    break;
                }
                i++;
                jedis.set("test" + i, i + "");
            }
            System.out.println(i + "次");
        } finally {
            jedis.close();
        }


    }


    // javaapi读写性能
    @Test
    public void pipeline() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("redis-config.xml");
        JedisPoolConfig jedisPoolConfig = applicationContext.getBean(JedisPoolConfig.class);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig,"localhost",6379);
        Jedis jedis= jedisPool.getResource();
        long start = System.currentTimeMillis();
        // 开启流水线
        Pipeline pipeline=jedis.pipelined();
        // 测试10w条数据读写
        for(int i = 0; i < 1000000; i++) {
            int j = i + 1;
            pipeline.set("key" + j, "value" + j);
            pipeline.get("key" + j);
        }
        List<Object> result = pipeline.syncAndReturnAll();
        long end = System.currentTimeMillis();
        // 计算耗时
        System.out.println("耗时" + (end - start) + "毫秒");


    }


    // redisTemplate读写性能
    @SuppressWarnings({ "resource", "rawtypes", "unchecked", "unused" })
    @Test
    public void testPipelineBySpring() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("redis-config.xml");
        RedisTemplate rt = (RedisTemplate)applicationContext.getBean("redisTemplate");
//        SessionCallback<Object> callback = (SessionCallback)(RedisOperations ops)->{
//            for(int i = 0; i < 100000; i++) {
//                int j = i + 1;
//                ops.boundValueOps("key" + j).set("value" + j);
//                ops.boundValueOps("key" + j).get();
//            }
//            return null;
//        };
        SessionCallback sessionCallback = new SessionCallback() {
            @Override
            public Object execute(RedisOperations ops) {
                for(int i = 0; i < 100000; i++) {
                    int j = i + 1;
                    ops.boundValueOps("key" + j).set("value" + j);
                    ops.boundValueOps("key" + j).get();
                }
                return null;
            }
        };
        long start = System.currentTimeMillis();
        // 执行Redis的流水线命令
        List result = rt.executePipelined(sessionCallback);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}
