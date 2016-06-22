package com.zju.lxf.share.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zju.lxf.share.R;
import com.zju.lxf.share.model.Service;

import java.util.List;


public class MyServiceListViewAdapter extends BaseAdapter{
    private List<Service> mData;
    private LayoutInflater mInflater;

    public MyServiceListViewAdapter(List<Service> mData, LayoutInflater mInflater){
        this.mData = mData;
        this.mInflater = mInflater;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Service service = (Service)getItem(i);
        if(view == null){
            view = mInflater.inflate(R.layout.my_service_item, null);
            TextView name = (TextView)view.findViewById(R.id.service_name);
            TextView description = (TextView)view.findViewById(R.id.service_description);
            TextView lastInvokedTime = (TextView)view.findViewById(R.id.service_last_invoke_time);
            TextView invokeCount = (TextView)view.findViewById(R.id.invoke_count);

            name.setText(service.getName());
            description.setText(service.getDescription());
            lastInvokedTime.setText(service.getLastInvokedTime());
            invokeCount.setText(String.valueOf(service.getInvokeCount()));

        }
        return view;
    }
}
