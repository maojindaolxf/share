package com.zju.lxf.share.nsd;

import android.content.Context;
import android.net.nsd.NsdServiceInfo;
import android.net.nsd.NsdManager;
import android.util.Log;

public class NsdHelper {

    private static NsdHelper nsdHelper = null;

    private NsdManager mNsdManager;

    private NsdHelper() {

    }

    private NsdHelper(Context context) {
        mNsdManager = (NsdManager) context.getSystemService(Context.NSD_SERVICE);
    }

    public static NsdHelper getInstance(Context context) {
        if (nsdHelper == null) {
            synchronized (NsdHelper.class) {
                if (nsdHelper == null) {
                    nsdHelper = new NsdHelper(context);
                }
            }
        }
        return nsdHelper;
    }

    public void registerService(String serviceName, int port, String serviceType, NsdManager.RegistrationListener registrationListener) {
        NsdServiceInfo serviceInfo = new NsdServiceInfo();
        serviceInfo.setPort(port);
        serviceInfo.setServiceName(serviceName);
        serviceInfo.setServiceType(serviceType);

        mNsdManager.registerService(
                serviceInfo, NsdManager.PROTOCOL_DNS_SD, registrationListener);

    }

    public void discoverServices(String serviceType, NsdManager.DiscoveryListener discoveryListener) {
        mNsdManager.discoverServices(
                serviceType, NsdManager.PROTOCOL_DNS_SD, discoveryListener);
    }

    public void stopDiscovery(NsdManager.DiscoveryListener discoveryListener) {
        mNsdManager.stopServiceDiscovery(discoveryListener);
    }

    public void resolveService(NsdServiceInfo serviceInfo, NsdManager.ResolveListener resolveListener) {
        mNsdManager.resolveService(serviceInfo, resolveListener);
    }

    public void tearDown(NsdManager.RegistrationListener registrationListener) {
        mNsdManager.unregisterService(registrationListener);
    }
}
