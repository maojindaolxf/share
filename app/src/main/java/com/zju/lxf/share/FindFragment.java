package com.zju.lxf.share;

import android.annotation.SuppressLint;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zju.lxf.share.model.Service;
import com.zju.lxf.share.nsd.NsdHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    public  static final String TAG = "findService";

    private static final int REFRESH_START = 0;
    private static final int REFRESH_COMPLETE = 1;
    private SwipeRefreshLayout mSwipeLayout;
    private ListView mListView;
    private ArrayAdapter<String> mAdapter;
    private List<String> mServices = new ArrayList<String>();

    private NsdHelper nsdHelper;
    private NsdManager.DiscoveryListener mDiscoveryListener;
    private NsdManager.ResolveListener mResolveListener;
    @SuppressLint("handlerLeak")
    private Handler mHandler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case REFRESH_START:
                    mServices.clear();
                    nsdHelper.discoverServices("_http._tcp.", mDiscoveryListener);
                    break;
                case REFRESH_COMPLETE:
                    nsdHelper.stopDiscovery(mDiscoveryListener);
                    mAdapter.notifyDataSetChanged();
                    mSwipeLayout.setRefreshing(false);
                    break;
            }
        };
    };

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        nsdHelper = NsdHelper.getInstance(this.getActivity());
        mDiscoveryListener = new NsdManager.DiscoveryListener() {
            @Override
            public void onServiceFound(NsdServiceInfo serviceInfo) {
                String serviceName = serviceInfo.getServiceName();
                if(!mServices.contains(serviceName)){
                    nsdHelper.resolveService(serviceInfo,mResolveListener);
                }
            }

            @Override
            public void onStartDiscoveryFailed(String serviceType, int errorCode) {
                Log.e(TAG, "start discovery failed, error code : " + errorCode);
            }

            @Override
            public void onStopDiscoveryFailed(String serviceType, int errorCode) {
                Log.e(TAG, "stop discovery failed, error code : " + errorCode);
            }

            @Override
            public void onDiscoveryStarted(String serviceType) {
                Log.i(TAG, "discovery started " + serviceType);
            }

            @Override
            public void onDiscoveryStopped(String serviceType) {
                Log.i(TAG, "discovery stopped " + serviceType);
            }

            @Override
            public void onServiceLost(NsdServiceInfo serviceInfo) {
                Log.e(TAG, "service lost " + serviceInfo);
            }
        };

        mResolveListener = new NsdManager.ResolveListener() {
            @Override
            public void onResolveFailed(NsdServiceInfo serviceInfo, int errorCode) {
                Log.e(TAG, "Resolve failed" + errorCode);
            }
            @Override
            public void onServiceResolved(NsdServiceInfo serviceInfo) {
                Log.e(TAG, "Resolve Succeeded. " + serviceInfo);
                mServices.add(serviceInfo.toString());
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.find_content, container, false);

        mListView = (ListView) view.findViewById(R.id.id_listview);
        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.id_swipe_ly);

        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeColors(R.color.refresh);
        mAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, mServices);
        mListView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onRefresh() {
        mHandler.obtainMessage(REFRESH_START).sendToTarget();
        mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 1000);
    }

}
