package com.report.common.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    static Map<String,Object> map = new ConcurrentHashMap<String,Object>();
    public static Object get(String key){
        return map.get(key);
    }

    public static void set(String key,Object value){
        map.put(key,value);
    }

}
