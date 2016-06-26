package com.zju.lxf.share.util;

import android.net.nsd.NsdManager;

import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Utils {
    public static final SimpleDateFormat invokeTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static Map<String, NsdManager.RegistrationListener> serviceToRegistrationListener = new ConcurrentHashMap<>();

    public static final JSONObject serviceBusinessLogical(String serviceName){
        JSONObject result = new JSONObject();
        switch (serviceName){
            case "Chat":


                break;
            case "FileShare":

                break;
            case "Calculator":

                break;
            default:
                break;
        }
        return result;
    }

}
