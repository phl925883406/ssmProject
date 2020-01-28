package com.imooc.myo2o.test.dataStructure.array;

import org.junit.Test;

public class array {
    @Test
    public void delete() {
        int i = removeDuplicates(new int[]{1, 2, 3, 3, 4});
        System.out.println(i);
    }


    public int removeDuplicates(int[] nums) {
        int a=0;
        int b=0;
        for (int i = 0; i < nums.length; i++) {
          a=  i;
            for (int j = 0; j < nums.length; j++) {
                b=  j;
            if (nums[i]==nums[j]){
//                for (int i1 = 0; i1 < nums.length; i1++) {
//                    if (i1>a){
//                        nums[i1-1]=nums[i1];
//                    }
//                }
            }
            }
        }
return nums.length;
    }
}
