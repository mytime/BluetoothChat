package com.jikexueyuan.bluetoothchat;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BLUETOOTH = 1000 ;
    private BluetoothAdapter bluetoothAdapter;
    private ListView lvDevices;
    private DevicesListAdapter devicesListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvDevices = (ListView) findViewById(R.id.lvDevices);
        devicesListAdapter = new DevicesListAdapter(this,android.R.layout.simple_list_item_1);
        lvDevices.setAdapter(devicesListAdapter);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (bluetoothAdapter==null){
            Toast.makeText(MainActivity.this,"无此设备",Toast.LENGTH_SHORT).show();
            finish();//关闭activity
        }else{
            if (!bluetoothAdapter.isEnabled()){

                //启用蓝牙
                requestEnableBlueTooth();
            }else {

                //查找蓝牙设备
                loadBondedDevices();

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case REQUEST_ENABLE_BLUETOOTH:
                switch (resultCode){
                    case RESULT_OK:
                        loadBondedDevices();
                        break;
                    default:
                        new AlertDialog.Builder(this)
                                .setTitle("提醒")
                                .setMessage("你拒绝启动蓝牙")
                                .setPositiveButton("再次启用", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //启用蓝牙
                                        requestEnableBlueTooth();
                                    }
                                }).setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        }).setCancelable(false).show();

                        break;
                }
        }
    }
    //启用蓝牙
    void requestEnableBlueTooth(){
        Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(i, REQUEST_ENABLE_BLUETOOTH);
    }

    //查找蓝牙设备
    void loadBondedDevices(){
        Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();
        for (BluetoothDevice device:bondedDevices){
            devicesListAdapter.add(new BluetoothDeviceWrapper(device));
        }

    }
}
