package com.example.demo.common.units;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//String to JosnFormat
public class JsonUnit {

    public static String toJson(Object object){
        GsonBuilder gsonBuilder=new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson=gsonBuilder.create();
        return gson.toJson(object);
    }
}
