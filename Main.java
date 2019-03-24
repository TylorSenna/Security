package com.company;


import com.company.des.DES;
import sun.security.util.BitArray;

import java.util.BitSet;
import java.util.Timer;

public class Main {

    public static void main(String[] args) {

        String message = "abcdefgh";
        String key = "1234567";
        //把String 转换成 2进制 的BitSet
        BitArray bitArray = new BitArray(64,message.getBytes());
        BitArray bitArraykey = new BitArray(56,key.getBytes());
        BitSet M_bitSet = new BitSet(64);
        BitSet key_bit = new BitSet(56);

        for(int i=0; i<64; i++){
            if (bitArray.get(i)){
                M_bitSet.set(i);
            }
        }/*       //不用输入message，直接对M_bitSet进行赋值
        M_bitSet.set(0); 
        M_bitSet.set(63);
        M_bitSet.set(62);
        M_bitSet.set(61);
        M_bitSet.set(60);*/

        for(int i=0; i<56; i++){
            if (bitArraykey.get(i)){
                key_bit.set(i);
            }
        }

        //key_bit.set(55);

        //开始加解密

        System.out.println(M_bitSet);
        System.out.println(bitArray);
        System.out.println(message);
        System.out.println("开始加密：-------------------------------------------------------");
        long startTime=System.currentTimeMillis();

        DES des = new DES(key_bit);
        BitSet C = des.Encrypt(M_bitSet,0);
        System.out.println(C);

        BitArray result_bitArray = new BitArray(64);
        for(int i=0; i<64; i++){
            if (C.get(i)){
                result_bitArray.set(i,true);
            }else {
                result_bitArray.set(i,false);
            }
        }
        byte[] result_bytes = result_bitArray.toByteArray();
        String result = "";
        for(int i=0; i<result_bytes.length; i++){
            result += (char)result_bytes[i];
        }
        System.out.println(result_bitArray);
        System.out.println(result);

        System.out.println("开始解密：-------------------------------------------------------");

        BitArray result_bitArray2 = new BitArray(64);
        BitSet C2 = des.Encrypt(C,1);
        System.out.println(C2);

        for(int i=0; i<64; i++){
            if (C2.get(i)){
                result_bitArray2.set(i,true);
            }else {
                result_bitArray2.set(i,false);
            }
        }
        byte[] result_bytes2 = result_bitArray2.toByteArray();
        result = "";
        for(int i=0; i<result_bytes2.length; i++){
            result += (char)result_bytes2[i];
        }
        System.out.println(result_bitArray2);
        System.out.println(result);
        long endTime=System.currentTimeMillis();
        System.out.println("当前程序耗时："+(endTime-startTime)+"ms");
    }
}