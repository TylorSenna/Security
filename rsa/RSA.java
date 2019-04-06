package com.company.rsa;

import java.math.BigInteger;

public class RSA {
    public BigInteger[][] genKey(BigInteger p, BigInteger q){
        BigInteger n = p.multiply(q) ;
        //fy为欧拉函数=(p-1)(q-1)
        BigInteger fy = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)) ;
        BigInteger e = new BigInteger("65537") ;
        BigInteger a = e ;
        BigInteger b = fy ;
        BigInteger[] rxy = new GCD().extGcd(a, b) ;
        BigInteger r = rxy[0] ;
        BigInteger x = rxy[1] ;
        BigInteger y = rxy[2] ;

        BigInteger d = x ;
        // 公钥  私钥
        return new BigInteger[][]{{n , e}, {n , d}} ;
    }

    /**
     * 加密
     * @param m 被加密的信息转化成为大整数m
     * @param pubkey 公钥
     */
    public BigInteger encrypt(BigInteger m, BigInteger[] pubkey){
        BigInteger n = pubkey[0] ;
        BigInteger e = pubkey[1] ;
        Exponentiation exponentiation = new Exponentiation();
        BigInteger c = exponentiation.expMode(m, e, n) ;
        return c ;
    }

    /**
     * 解密
     * @param c
     * @param selfkey 私钥
     */
    public BigInteger decrypt(BigInteger c, BigInteger[] selfkey){
        BigInteger n = selfkey[0] ;
        BigInteger d = selfkey[1] ;
        Exponentiation exponentiation = new Exponentiation();
        BigInteger m = exponentiation.expMode(c, d, n) ;
        return m ;
    }

    /**
     * 签名
     * @param hm 消息经过hash后的值
     * @param selfkey 私钥
     */
    public BigInteger sign(BigInteger hm, BigInteger[] selfkey){
        BigInteger n = selfkey[0] ;
        BigInteger d = selfkey[1] ;
        Exponentiation exponentiation = new Exponentiation();
        BigInteger s = exponentiation.expMode(hm, d, n) ;
        return s ;
    }

    /**
     * 加密
     * @param s 签名
     * @param pubkey 公钥
     */
    public BigInteger verify(BigInteger s, BigInteger[] pubkey){
        BigInteger n = pubkey[0] ;
        BigInteger e = pubkey[1] ;
        Exponentiation exponentiation = new Exponentiation();
        BigInteger v = exponentiation.expMode(s, e, n) ;
        return v ;
    }

}
