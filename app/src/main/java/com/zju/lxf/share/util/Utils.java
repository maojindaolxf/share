package com.zju.lxf.share.util;

import android.net.nsd.NsdManager;

import com.alibaba.fastjson.JSONObject;
import com.zju.lxf.share.nsd.NsdHelper;

import java.io.IOException;
import java.net.ServerSocket;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Utils {
    public static final SimpleDateFormat invokeTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static Map<String, NsdManager.RegistrationListener> serviceToRegistrationListener = new ConcurrentHashMap<>();
    public static Map<String, Integer> serviceToPort = new ConcurrentHashMap<>();

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

    public synchronized static int getAvailablePort(){
        int port = 0;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(0);
            port = serverSocket.getLocalPort();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return port;
    }

    public static void tearDownService(NsdHelper nsdHelper){
        for(String key : serviceToRegistrationListener.keySet()){
            nsdHelper.tearDown(serviceToRegistrationListener.get(key));
        }
    }
}
