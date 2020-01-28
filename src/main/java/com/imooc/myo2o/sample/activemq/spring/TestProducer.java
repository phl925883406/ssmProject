package com.imooc.myo2o.sample.activemq.spring;

import com.imooc.myo2o.sample.activemq.ActiveMQUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= "classpath:spring-jms.xml")
public class TestProducer {
    @Autowired
    private Producer producer;
    
    @Before
    public void checkServer() {
    	ActiveMQUtil.checkServer();
    }
    
    @Test
    public void testSend(){
    	for (int i = 0; i < 100; i++) {
            producer.sendTextMessage("消息 " + i);
		}
    }
}