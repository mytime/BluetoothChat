package com.jikexueyuan.bluetoothchat;

import android.bluetooth.BluetoothDevice;

/**
 * Created by iwan on 2016/2/19.
 */
public class BluetoothDeviceWrapper {

    private final BluetoothDevice device;

    public BluetoothDeviceWrapper(BluetoothDevice device){
        this.device = device;
    }

    public BluetoothDevice getDevice(){
        return device;
    }

    @Override
    public boolean equals(Object o) {
        if (o!=null){
            //断言
            assert o instanceof BluetoothDeviceWrapper;

            return ((BluetoothDeviceWrapper)o).getDevice().getAddress().equals(getDevice().getAddress());
        }else{
            return false;
        }

    }
}

