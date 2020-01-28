package com.imooc.myo2o.test.springUtilsTest;

import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.Arrays;

public class springUtilsTest {

    public static void main(String[] args) {
        int[] a = new int[]{1, 2,3};

        int[] b = new int[3];

        System.arraycopy(a,0,b,1,2);
        System.out.println(Arrays.toString(b));
    }


    @Test
    public void trimWhitespace(){
//        System.out.println( StringUtils.trimWhitespace("   aaaa    "));
                System.out.println( StringUtils.delete("   aaabbb    ","a"));
    }
}
