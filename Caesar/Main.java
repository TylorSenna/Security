package com.company;


import com.company.des.DES;
import sun.security.util.BitArray;

import java.util.BitSet;
import java.util.Timer;

public class Main {

    public static void main(String[] args) {   //Caesar 测试

        String test = new String ("worry is a waste of time, it will not change anything, can only spoil your head, and steal your happiness.");
        String []encrypted_result = new String[25];
        String []dencrypted_result = new String[25];

        for(int i=1; i<26;i++){//加密
            encrypted_result[i-1] = Caesar.Encrypt(test,i);
        }

        //随机取一个加密后的数据进行解密
        for(int i=1; i<26;i++){//解密
            dencrypted_result[i-1] = Caesar.Decrypt(encrypted_result[5],i);
            String []stringlist = dencrypted_result[i-1].split("\\pP|\\pS|\\pM|\\pN|\\pC| ");  //以上匹配各种除字母以外的符号以及空格
            int x = 0;
            for(String s: stringlist){
                if(SpellCorrect.correct(s) || s.equals("")){//由于分割后可能会有空字符串（标点符号的位置），所以要跳过此子字符串
                    //System.out.println(s+"  "+SpellCorrect.correct(s));
                    x++;
                    if(x==stringlist.length){   //判断所有单词都正确
                        System.out.println("对加密后的数据："+encrypted_result[5]+"  需要把每个字符向左偏移"+ i +"位，才能解密出正确结果："+dencrypted_result[i-1]);
                        return;
                    }
                }else {
                    break;
                }
            }
        }
    }
}




