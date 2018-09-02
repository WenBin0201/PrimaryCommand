package com.pc.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wenbinbin on 2018/8/29.
 */

public class GsonUtil {


    private static Gson gson = new Gson();
    private static JsonParser parser = new JsonParser();


//    public static <T> T getBean(String s, T t){
//        Log.e("HttpUtil","=="+ s);
//        return json2Obj(s,t.c);
//
//    }

    /**
     * json字符串转json对象
     * @param jsonData
     * @return
     */
    public static JsonObject getJsonObject(String jsonData){
        return new JsonParser().parse(jsonData).getAsJsonObject();
    }

    public static String removeProperty(String jsonString,String... key){
        JsonObject jsonObject =  getJsonObject(jsonString);
        for (String s:key){
            jsonObject.remove(s);
        }
        return jsonObject.toString();
    }

    /**
     * json字符串转json数组
     * @param JsonData
     * @return
     */
    public static JsonArray getJsonArray(String JsonData){
        return new JsonParser().parse(JsonData).getAsJsonArray();
    }

    /**
     * @Description: 对象转json
     * String
     */
    public static String obj2Json(Object obj){
        return gson.toJson(obj);
    }

    /**
     * @Description: json转对象
     * T
     */
    public static <T> T json2Obj(String json, Class<T> clazz){
        return gson.fromJson(json, clazz);
    }

    /**
     * @Description: json转对象<泛型类型>
     * T
     */
    public static <T> T json2Obj4ReflectType(String json, TypeToken<T> typeToken){
        return gson.fromJson(json, typeToken.getType());
    }

    /**
     * @Description: json转对象集合
     * List<T>
     */
    public static <T> List<T> parseList(String jsonArray, Class<T> clazz) {
        List<T> dataList = new ArrayList<>();
        JsonElement el = parser.parse(jsonArray);
        JsonArray array = null;
        if (el.isJsonArray()) {
            array = el.getAsJsonArray();
        }
        Iterator<JsonElement> it = array.iterator();
        while (it.hasNext()) {
            JsonElement e = (JsonElement) it.next();
            // JsonElement转换为JavaBean对象
            dataList.add(gson.fromJson(e, clazz));
        }
        return dataList;
    }

    public static <T> List<T> parseJsonArrayList(JsonArray jsonArray, Class<T> clazz) {
        if (jsonArray ==null){
            return null;
        }
        List<T> dataList = new ArrayList<>();
        Iterator<JsonElement> it = jsonArray.iterator();
        while (it.hasNext()) {
            JsonElement e = (JsonElement) it.next();
            // JsonElement转换为JavaBean对象
            dataList.add(gson.fromJson(e, clazz));
        }
        return dataList;
    }

    /**
     * @Description: 解析json对象写入节点
     * String
     */
    public static String parseJsonPutNode(String jsonData,String node,String value){
        JsonObject jsonObj = new JsonParser().parse(jsonData).getAsJsonObject();
        if(jsonObj==null){
            return null;
        }
        jsonObj.addProperty(node, value);
        return jsonObj.toString();
    }

    /**
     * @Description: json对象写入节点
     * String
     */
    public static void putNode(JsonObject jsonObj,String node,String value){
        if(jsonObj==null){
            return;
        }
        jsonObj.addProperty(node, value);
    }


    /**
     * @Description: 直接解析json对象
     * String
     */
    public static JsonElement parseJsonGetNode(String jsonData,String node){
        JsonObject jsonObj = new JsonParser().parse(jsonData).getAsJsonObject();
        return jsonObj.get(node);
    }


    public static JsonElement getJsonObjectNode(JsonObject jsonObj,String node){
        if(jsonObj==null||jsonObj.get(node)==null){
            return null;
        }
        return jsonObj.get(node);

    }

}
