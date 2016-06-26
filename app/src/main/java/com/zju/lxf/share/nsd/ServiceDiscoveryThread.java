package com.zju.lxf.share.nsd;

import android.annotation.SuppressLint;
import android.net.nsd.NsdManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxf on 2016/6/26.
 */
@Deprecated
public class ServiceDiscoveryThread extends HandlerThread{
    private static final String TAG = "ServiceDiscoveryThread";
    private static final int MESSAGE_S_D = 0;
    private Handler mHandler;
    private Handler mResponseHandler;
    private NsdHelper nsdHelper;
    private NsdManager.DiscoveryListener mDiscoveryListener;

    public ServiceDiscoveryThread(Handler mResponseHandler, NsdHelper nsdHelper, NsdManager.DiscoveryListener mDiscoveryListener){
        super(TAG);
        this.mResponseHandler = mResponseHandler;
        this.nsdHelper = nsdHelper;
        this.mDiscoveryListener = mDiscoveryListener;
    }

    @SuppressLint("HandlerLeak")
    @Override
    protected void onLooperPrepared() {
        mHandler = new Handler(){
            public void handlerMessage(Message msg){
                if(msg.what == MESSAGE_S_D){
                    nsdHelper.discoverServices("_http._tcp.", mDiscoveryListener);
                }
            }
        };
    }
}
