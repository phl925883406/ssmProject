package com.imooc.myo2o.util.redis;

import redis.clients.jedis.Jedis;

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


}
