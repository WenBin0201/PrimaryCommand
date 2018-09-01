package com.pc.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Created by wenbinbin on 2018/8/29.
 */

public class GsonUtil {

    private static Gson gson = new Gson();
    private static JsonParser parser = new JsonParser();

    /**
     * 字符串转json对象
     * @param jsonData
     * @return
     */
    public static JsonObject getJsonObject(String jsonData){
        return parser.parse(jsonData).getAsJsonObject();
    }

    /**
     * 字符串转json数组
     * @param jsonData
     * @return
     */
    public static JsonArray getJsonArray(String jsonData){
        return parser.parse(jsonData).getAsJsonArray();
    }

    /**
     * 移除json节点
     * @param jsonString
     * @param key
     * @return
     */
    public static String removeProperty(String jsonString,String ...key){
        JsonObject jsonObject = getJsonObject(jsonString);
        for(String s:key){
            jsonObject.remove(s);
        }
        return jsonObject.toString();
    }

    /**
     * 对象转json
     * @param o
     * @return
     */
    public static String objToJson(Object o){
        return gson.toJson(o);
    }

    /**
     * json 转对象
     * @param json
     * @param cs
     * @param <T>
     * @return
     */
    public static <T> T jsonToObj(String json,Class<T> cs){
        return gson.fromJson(json,cs);
    }

}
