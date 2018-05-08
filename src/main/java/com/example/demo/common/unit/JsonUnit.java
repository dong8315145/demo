package com.example.demo.common.unit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUnit {

    public static String toJson(Object object){
        GsonBuilder gsonBuilder=new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson=gsonBuilder.create();
        return gson.toJson(object);
    }
}
