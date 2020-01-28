package com.imooc.myo2o.test.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;

public class test {

    @Test
    public void token() {
        JwtBuilder builder = Jwts.builder().setId("888").setSubject("小白").setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "itcast");
        System.out.println(builder.compact());
    }

    @Test
    public void name() {
        String
                token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiLlsI_nmb0iLCJpYXQiOjE1NzA2OTA5MTMsImV4cCI6MTU3MDY5MDk3Miwicm9sZXMiOiJhZG1pbiIsImxvZ28iOiJsb2dvLnBuZyJ9.3O4G10GBtQG7YJiT6V9s3Vp13SbQIec4J-892XjFlL8";
        Claims claims =
                Jwts.parser().setSigningKey("itcast").parseClaimsJws(token).getBody();
        System.out.println("id:" + claims.getId());
        System.out.println("subject:" + claims.getSubject());
        System.out.println("IssuedAt:" + claims.getIssuedAt());
        System.out.println("roles:"+claims.get("roles"));
        System.out.println("logo:"+claims.get("logo"));
    }

    @Test
    public void name1() {
        //为了方便测试，我们将过期时间设置为1分钟
        long now = System.currentTimeMillis();//当前时间
        long exp = now + 1000 * 60;//过期时间为1分钟
        JwtBuilder builder = Jwts.builder().setId("888")
                .setSubject("小白")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "itcast")
                .setExpiration(new Date(exp))
                .claim("roles", "admin") //自定义claims存储数据
                .claim("logo", "logo.png");
        System.out.println(builder.compact());
    }
}
