package com.jikexueyuan.bluetoothchat;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iwan on 2016/2/19.
 */
public class DevicesListAdapter extends BaseAdapter {


    private final Context context;
    private final int cellResId;
    private List<BluetoothDeviceWrapper> items = new ArrayList<>();

    public DevicesListAdapter(Context context,int cellResId) {
        this.context = context;
        this.cellResId = cellResId;

    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(cellResId,null);
        }
        //断言，convertView必须是一个TextView
        assert convertView instanceof TextView;
        ((TextView) convertView).setText(getItem(position).toString());
        return convertView;
    }

    public void add(BluetoothDeviceWrapper bluetoothDeviceWrapper) {
        if(!items.contains(bluetoothDeviceWrapper)){
            items.add(bluetoothDeviceWrapper);
            notifyDataSetChanged();
        }
    }
}
