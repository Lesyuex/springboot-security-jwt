package com.jobeth.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述
 *
 * @author Jobeth
 * @date Created by IntelliJ IDEA on 11:34 2020/4/10
 */
public class JsonUtil {

    public static String toJsonString(Object object)  {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static <T> T parseObject(String json, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }


    public static <T> T parseObjectByType(String json, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<T>() {}.getType());
    }

    public static void main(String[] args) throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("id",1);
        map.put("username","123");
        map.put("username2","");
        map.put("testNull",null);
        System.out.println(toJsonString(map));
    }

}

