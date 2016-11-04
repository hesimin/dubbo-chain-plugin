package com.github.hesimin.dubbo.plugin;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hesimin
 * @date 2016/10/17
 */
public class DubboThreadLocal {
    private static final ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal();
    public static final String GLOBAL_REQUEST_ID_KEY = "global_request_id_key";
    public static final String NODE_REQUEST_ID_KEY = "node_request_id_key";
    public static final String REQUEST_DUBBO_COUNT_KEY = "request_dubbo_count_key";// 消费端调用dubbo请求计数
    public static final String IS_WEB_REQUEST_KEY = "is_web_request_key";

    public static Object get(String key) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            return null;
        } else {
            return map.get(key);
        }
    }

    public static String getString(String key) {
        return (String) get(key);
    }

    public static void put(String key, Object value) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>();
            threadLocal.set(map);
        }
        map.put(key, value);
    }

    public static void clear() {
        threadLocal.remove();
    }

    public static int getRequestDubboCount() {
        Object count = get(REQUEST_DUBBO_COUNT_KEY);
        if (count == null) {
            return 0;
        } else {
            return (int) count;
        }
    }

    public static void increaseRequestDubboCount() {
        int count = getRequestDubboCount();
        put(REQUEST_DUBBO_COUNT_KEY, ++count);
    }

    public static boolean isWebRequest() {
        Object flag = get(IS_WEB_REQUEST_KEY);
        if (flag == null) {
            return false;
        } else {
            return (boolean) flag;
        }
    }
}
