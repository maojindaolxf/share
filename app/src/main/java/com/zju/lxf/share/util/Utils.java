package com.zju.lxf.share.util;

import android.net.nsd.NsdManager;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Utils {
    public static final SimpleDateFormat invokeTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static Map<String, NsdManager.RegistrationListener> serviceToRegistrationListener = new ConcurrentHashMap<>();
}
