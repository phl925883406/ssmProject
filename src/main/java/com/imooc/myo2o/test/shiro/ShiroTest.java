package com.imooc.myo2o.test.shiro;

import com.imooc.myo2o.entity.User;
import com.imooc.myo2o.service.UserService;
import com.imooc.myo2o.service.impl.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

public class ShiroTest {
    @Test
    public void testHelloworld() {
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager  Factory<org.apache.shiro.mgt.SecurityManager> factory =

        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //2、得到SecurityManager实例 并绑定给SecurityUtils   org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        try {
            //4、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //5、身份验证失败
        }
        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录
        //6、退出
        subject.logout();
    }

    @Test
    public void name() {
//        String password = "123";
//        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
//        int times = 2;
//        String algorithmName = "md5";
//
//        String encodedPassword = new SimpleHash(algorithmName,password,salt,times).toString();
//
//        System.out.printf("原始密码是 %s , 盐是： %s, 运算次数是： %d, 运算出来的密文是：%s ",password,salt,times,encodedPassword);
//        User user = new User();
//        user.setName("tom");
//        user.setPassword("encodedPassword");
//        user.setSalt(salt);
//        user.setId(3L);
//        UserService userService=new UserServiceImpl();
//        userService.add(user);

        User user = new User();
        user.setName("tom");
        user.setPassword("123");
        if (login(user)) {
            System.out.println("登录成功");
        } else {
            System.out.println("登录失败");
        }
    }


    private static boolean hasRole(User user, String role) {
        Subject subject = getSubject(user);
        return subject.hasRole(role);
    }

    private static boolean isPermitted(User user, String permit) {
        Subject subject = getSubject(user);
        return subject.isPermitted(permit);
    }

    private static Subject getSubject(User user) {
        //加载配置文件，并获取工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //获取安全管理者实例
        SecurityManager sm = factory.getInstance();
        //将安全管理者放入全局对象
        SecurityUtils.setSecurityManager(sm);
        //全局对象通过安全管理者生成Subject对象
        Subject subject = SecurityUtils.getSubject();

        return subject;
    }

    private static boolean login(User user) {
        Subject subject = getSubject(user);
        //如果已经登录过了，退出
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        //封装用户的数据
        UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword());
        try {
            //将用户的数据token 最终传递到Realm中进行对比
            subject.login(token);
        } catch (AuthenticationException e) {
            //验证错误
            return false;
        }

        return subject.isAuthenticated();
    }
}
