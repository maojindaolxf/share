package com.zju.lxf.share;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zju.lxf.share.adapter.MyServiceListViewAdapter;
import com.zju.lxf.share.model.Service;

import java.util.ArrayList;
import java.util.List;

public class MyFragment extends Fragment {
    private ListView myServiceListView;
    private List<Service> mServiceData;
    private AdapterView.OnItemClickListener itemClickListener;

    private void initServiceData(){
        mServiceData = new ArrayList<>();
        Service s1 = new Service("Chat", "和身边的朋友打个招呼吧", null);
        Service s2 = new Service("FileShare", "传输文件有时很简单", null);
        Service s3 = new Service("Calculator", "简易远程计算器", null);

        mServiceData.add(s1);mServiceData.add(s2);mServiceData.add(s3);

        itemClickListener = new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ServiceDetailSettingsActivity.class);
                Object o = adapterView.getItemAtPosition(i);
                if(o instanceof  Service){
                    intent.putExtra(ServiceDetailSettingsActivity.TAG, (Service)o);
                }
                startActivity(intent);
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_service_content, container, false);

        initServiceData();
        myServiceListView = (ListView) view.findViewById(R.id.my_service_list_view);
        myServiceListView.setAdapter(new MyServiceListViewAdapter(mServiceData, inflater));
        myServiceListView.setOnItemClickListener(itemClickListener);

        return view;
    }
}
