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
        Random random = new Random();
        BigInteger pp= BigInteger.probablePrime(518,random);
        BigInteger qq= BigInteger.probablePrime(506,random);
        int count=0;
        BigInteger[] bigIntegers = new BigInteger[2];

        while(count<1){
            if(pp.isProbablePrime(100)){
                BigInteger p1 = BigInteger.ZERO;
                p1 = p1.add(pp);
                p1 = (p1.subtract(BigInteger.ONE)).divide(BigInteger.valueOf(2));
                if((p1.isProbablePrime(100))){
                    bigIntegers[count] = pp;
                    count++;
                }
            }
            pp= BigInteger.probablePrime(518,random);
        }
        while(count<2){
            if(qq.isProbablePrime(100)){
                BigInteger q1 = BigInteger.ZERO;
                q1 = q1.add(qq);
                q1 = (q1.subtract(BigInteger.ONE)).divide(BigInteger.valueOf(2));
                if((q1.isProbablePrime(100))){
                    bigIntegers[count] = qq;
                    count++;
                }
            }
            qq= BigInteger.probablePrime(506,random);
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

