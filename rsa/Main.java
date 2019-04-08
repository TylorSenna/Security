package com.company;


import com.company.des.DES;
import com.company.rsa.Convert;
import com.company.rsa.RSA;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.util.Random;
import java.util.Timer;

public class Main {

    public static void main(String[] args) {

        Random random = new Random();
        BigInteger pp= BigInteger.probablePrime(1024,random);
        int count=0;
        BigInteger[] bigIntegers = new BigInteger[2];

        while(count<2){
            if(pp.isProbablePrime(100)){
                bigIntegers[count] = pp;
                count++;
            }
            pp = pp.multiply(BigInteger.ONE.add(BigInteger.ONE));       //如果要p=2p+1 q=2q+1是素数，那么p，q是强素数。这里没有做此判断
            pp=pp.nextProbablePrime();
        }
        // 公钥私钥中用到的两个大质数p,q
        BigInteger p = bigIntegers[0];
        BigInteger q = bigIntegers[1];

        RSA rsa = new RSA() ;
        // 生成公钥私钥
        BigInteger[][] keys = rsa.genKey(p, q) ;
        BigInteger[] pubkey  = keys[0] ;
        BigInteger[] selfkey = keys[1] ;

        // 需要被加密的信息转化成数字，如果信息长度大于n的长度，那么分段进行加密，分段解密。
        String text = "abcdef,hello,world!胡国煜";
        System.out.println("被加密信息：" + text);
        BigInteger m = Convert.StringToBig(text);
        System.out.println("被加密信息经过base64转换后：" + m);
        // 加密
        long startTime=System.currentTimeMillis();
        BigInteger c = rsa.encrypt(m, pubkey) ;
        System.out.println("密文：" + c);
        // 解密
        BigInteger d = rsa.decrypt(c, selfkey) ;
        System.out.println("被解密后信息：" + d);
        String result = Convert.BigToString(d);
        System.out.println("解密完经过base64转换的字符串："+result);
        long endTime=System.currentTimeMillis();
        System.out.println("当前程序耗时："+(endTime-startTime)+"ms");

        BigInteger Hm = BigInteger.valueOf(m.hashCode());
        System.out.println("签名前：" + Hm);
        // 签名
        BigInteger s = rsa.encrypt(m, selfkey) ;
        System.out.println("签名：" + s);
        // 认证
        BigInteger v = rsa.decrypt(s, pubkey) ;
        System.out.println("被认证后信息：" + v);
    }
}

