package com.example.whatsapp.helper;

import android.util.Base64;

public class Base64Custom {

    public static String EncodeBase64(String userId){
        return Base64.encodeToString(userId.getBytes(), Base64.DEFAULT).replaceAll("(\\n|\\r)", "");
    }

    public static String DecodeBase64(String userId){
        return new String(Base64.decode(userId, Base64.DEFAULT));
    }


}
