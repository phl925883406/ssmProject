package com.imooc.myo2o.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RequestMapping("redis")
public class RedisController {


    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping("testredis")
    @ResponseBody
    public String testRedis() {
        redisTemplate.opsForValue().set("testkey", "testvalue");
        Object testkey = redisTemplate.opsForValue().get("testkey");
        System.out.println(testkey);
        return (String) testkey;
    }




}
