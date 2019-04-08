package com.company.rsa;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.math.BigInteger;

public class Convert {

    public static BigInteger StringToBig(String text){
        String code = new String(Base64.encode(text.getBytes()));
        byte[] bytes = code.getBytes();
        StringBuilder stringBuilder = new StringBuilder("111");      //"111"作为开始限定符  因为string转bigInteger 首位是0的话会丢掉
        for(int i=0; i<bytes.length; i++){
            int temp = bytes[i];
            String str = Integer.toString(temp);
            while(str.length()<3){
                str= "0"+str;
            }
            stringBuilder.append(str);
        }
        BigInteger m = new BigInteger(stringBuilder.toString());
        return m;
    }

    public static String BigToString(BigInteger d){
        String result = d.toString();
        result = result.substring(3,result.length());
        byte[] bytes2 = new byte[result.length()/3];
        for(int i=0,j=0; i<bytes2.length;i++){
            bytes2[i] = Byte.parseByte(result.substring(j,j+3));
            j+=3;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i<bytes2.length; i++){
            stringBuilder.append((char)bytes2[i]);
        }
        String result2 = new String(Base64.decode(stringBuilder.toString()));
        return result2;
    }
}
