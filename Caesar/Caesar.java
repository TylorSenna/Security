package com.company;

public class Caesar {

    public static String Encrypt(String p_text, int num){
        char s[] = null;
        s = p_text.toCharArray();
        for(int i =0; i<p_text.length(); i++){
            if(s[i]>='a' && s[i] <= 'z'){
                if(s[i]+num > 'z') {
                    s[i] = (char) (s[i] + num - 26);
                }else {
                    s[i] = (char) (s[i] + num);
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (char a:s){
            stringBuilder.append(a);
        }
        return stringBuilder.toString();
}

    public static String Decrypt(String c_text, int num){
        char s[] = null;
        s = c_text.toCharArray();
        for(int i =0; i<c_text.length(); i++){
            if(s[i]>='a' && s[i] <= 'z') {
                if(s[i]-num < 'a') {
                    s[i] = (char) (s[i] - num + 26);
                }else {
                    s[i] = (char) (s[i] - num);
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (char a:s){
            stringBuilder.append(a);
        }
        return stringBuilder.toString();
    }
}
