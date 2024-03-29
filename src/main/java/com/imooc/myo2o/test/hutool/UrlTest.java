package com.imooc.myo2o.test.hutool;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.imooc.myo2o.entity.Permission;
import org.junit.Test;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static java.lang.annotation.ElementType.METHOD;

public class UrlTest {
    @Test
    @Comment("URLUtil使用举例")
    public void test1() {
        String url1 = "how2j.cn";
        String url2 = "http://how2j.cn/k/tmall_springboot/tmall_springboot-1799/1799.html";
        String urla = URLUtil.formatUrl(url1);
        String urlb = URLUtil.encode(url2);
        String urlc = URLUtil.decode(urlb);
        String urld = URLUtil.getPath(url2);

        p1("原数据", url1, "格式化之后", urla);
        p1("原数据", url2, "编码数据", urlb);
        p1("编码数据", urlb, "解码数据", urlc);
        p1("原数据", url2, "对应路径路径", urld);

    }

    private String preComment = null;

    private void c(String msg) {
        System.out.printf("\t备注：%s%n", msg);
    }

    private void p1(String type1, Object value1, String type2, Object value2) {
        p(type1, value1, type2, value2, "format1");
    }

    private void p2(String type1, Object value1, String type2, Object value2) {
        p(type1, value1, type2, value2, "format2");
    }

    private void p3(String type1, Object value1) {
        p(type1, value1, "", "", "format3");
    }

    private void p(String type1, Object value1, String type2, Object value2, String format) {
        try {
            throw new Exception();
        } catch (Exception e) {

            String methodName = getTestMethodName(e.getStackTrace());
            Method m = ReflectUtil.getMethod(this.getClass(), methodName);
            Comment annotation = m.getAnnotation(Comment.class);
            if (null != annotation) {
                String comment = annotation.value();
                if (!comment.equals(preComment)) {
                    System.out.printf("%n%s 例子： %n%n", comment);
                    preComment = comment;
                }

            }
        }
        int padLength = 12;
        type1 = StrUtil.padEnd(type1, padLength, Convert.toSBC(" ").charAt(0));
        type2 = StrUtil.padEnd(type2, padLength, Convert.toSBC(" ").charAt(0));
        if ("format1".equals(format)) {
            System.out.printf("\t%s的:\t\"%s\" %n\t被转换为----->%n\t%s的 :\t\"%s\" %n%n", type1, value1, type2, value2);
        }
        if ("format2".equals(format)) {
            System.out.printf("\t基于 %s:\t\"%s\" %n\t获取 %s:\t\"%s\"%n%n", type1, value1, type2, value2);
        }
        if ("format3".equals(format)) {
            System.out.printf("\t%s:\t\"%s\" %n\t%n", type1, value1);

        }
    }

    private String getTestMethodName(StackTraceElement[] stackTrace) {
        for (StackTraceElement se : stackTrace) {
            String methodName = se.getMethodName();
            if (methodName.startsWith("test")) {
                return methodName;
            }
        }
        return null;
    }

    @Target({METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Inherited
    @Documented
    public @interface Comment {
        String value();
    }


    @Test
    public void nul() {
        Map map = new HashMap<>();
        Boolean goodsAmountPaid = (Boolean) map.get("goodsAmountPaid");
        if (!goodsAmountPaid) {
            System.out.println(111);
        }
    }
}
