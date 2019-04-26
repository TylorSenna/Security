package com.company;


import com.company.des.DES;
import com.company.rsa.Convert;
import com.company.rsa.RSA;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.util.Random;
import java.util.Timer;

public class Main {

    public static void main(String[] args) {

        long startTime=System.currentTimeMillis();

        RSA rsa = new RSA() ;
        // 生成公钥私钥
        // 需要被加密的信息转化成数字，如果信息长度大于n的长度，那么分段进行加密，分段解密。
        String text = "abcdefghi,胡国煜abcdefghi,胡国煜abcd,胡国煜abcd,胡国煜abcd,胡国煜abcd,胡国煜abcd,胡国煜abcd,胡国煜abcd,胡国煜abcd,胡国煜abcd";
        System.out.println("被加密信息：" + text);
        // 加密
        String c_result = rsa.encrypt_string(text);
        System.out.println("加密后的字符串："+c_result);
        // 解密
        String result4 = rsa.decrypt_string(c_result);
        System.out.println("解密完的字符串result4："+result4);
        long endTime=System.currentTimeMillis();
        System.out.println("当前程序耗时："+(endTime-startTime)+"ms");

        System.out.println("签名前：" + String.valueOf(text.hashCode() & Integer.MAX_VALUE));
        // 签名
        String s = rsa.sign_string(text) ;
        System.out.println("签名：" + s);
        // 认证
        String v = rsa.verify_string(s) ;
        System.out.println("被认证后信息：" + v);
    }
}

