package com.company;


import com.company.des.DES;
import sun.security.util.BitArray;

import java.util.BitSet;
import java.util.Timer;

public class Main {

    public static void main(String[] args) {

        String message = "abcdefghijklmnaskjdhl aksjdh库哈斯立刻解放哈伦裤杀了开始计划flak就是阿拉山口计划分厘卡时间和flak健身房";
        String key = "1234567";
        //开始加解密
        System.out.println(message);
        System.out.println("开始加密：-------------------------------------------------------");
        long startTime=System.currentTimeMillis();

        DES des = new DES(key);
        String c = des.encrypt_string(message);
        System.out.println("密文："+ c);
        System.out.println("开始解密：-------------------------------------------------------");
        String result = des.decrypt_string(c);
        System.out.println("解密后的明文："+ result);

        long endTime=System.currentTimeMillis();
        System.out.println("当前程序耗时："+(endTime-startTime)+"ms");
    }
}