package com.microsoft.util;

import com.google.gson.Gson;

/**
 * Created by huiliu on 2018/11/27.
 *
 * @email liu594545591@126.com
 * @introduce
 */
public class GsonUtil {

    /** 将Json数据解析成相应的映射对象 */
    public static <T> T parseJsonWithGson(String jsonData, Class<T> type) {
        Gson gson = new Gson();
        return gson.fromJson(jsonData, type);
    }

    /** 将对象数据解析成json */
    public static String parseBeanWithJson(Object type) {
        Gson gson = new Gson();
        return gson.toJson(type);
    }


}

