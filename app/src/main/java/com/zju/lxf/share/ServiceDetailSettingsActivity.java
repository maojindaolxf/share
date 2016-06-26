package com.zju.lxf.share;

import android.content.Intent;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zju.lxf.share.model.Service;
import com.zju.lxf.share.nsd.NsdHelper;
import com.zju.lxf.share.util.Utils;

public class ServiceDetailSettingsActivity extends AppCompatActivity {
    public  static final String TAG = "ServiceData";

    private Button mStartButton;
    private Button mStopButton;
    private NsdHelper nsdHelper;
    private NsdManager.RegistrationListener registrationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_detail_setting);

        Intent intent = getIntent();
        final Service service = intent.getParcelableExtra(TAG);

        final String serviceName = service.getName();
        ((TextView)findViewById(R.id.service_name)).setText(serviceName);
        ((TextView)findViewById(R.id.service_status)).setText(String.valueOf(service.getStatus()));
        ((TextView)findViewById(R.id.service_last_invoke_time1)).setText(service.getLastInvokedTime());
        ((TextView)findViewById(R.id.service_call_times)).setText(String.valueOf(service.getInvokeCount()));
        ((TextView)findViewById(R.id.service_des)).setText(service.getDescription());

        mStartButton = (Button) findViewById(R.id.start_service);
        mStopButton  = (Button) findViewById(R.id.stop_service);

        if(service.getStatus() == 1)
            mStartButton.setClickable(false);
        else
            mStopButton.setClickable(false);

        nsdHelper = NsdHelper.getInstance(this);

        if(Utils.serviceToRegistrationListener.containsKey(serviceName))
            registrationListener = Utils.serviceToRegistrationListener.get(serviceName);
        else {
            registrationListener = new NsdManager.RegistrationListener() {

                @Override
                public void onServiceRegistered(NsdServiceInfo nsdServiceInfo) {
                    Log.d(TAG, nsdServiceInfo.getServiceName());
                }

                @Override
                public void onRegistrationFailed(NsdServiceInfo serviceInfo, int errorCode) {
                }

                @Override
                public void onServiceUnregistered(NsdServiceInfo arg0) {
                }

                @Override
                public void onUnregistrationFailed(NsdServiceInfo serviceInfo, int errorCode) {
                }
            };
            Utils.serviceToRegistrationListener.put(serviceName, registrationListener);
        }

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nsdHelper.registerService(serviceName, 8000, "_http._tcp.", registrationListener);
            }
        });

        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nsdHelper.tearDown(registrationListener);
            }
        });
    }




}
