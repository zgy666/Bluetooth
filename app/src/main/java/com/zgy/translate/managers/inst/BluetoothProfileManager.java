package com.zgy.translate.managers.inst;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothHealth;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.os.ParcelUuid;
import android.util.Log;

import com.zgy.translate.MainActivity;
import com.zgy.translate.adapters.BluetoothBondedDeviceAdapter;
import com.zgy.translate.domains.dtos.BluetoothLeConnectionDTO;
import com.zgy.translate.domains.dtos.BluetoothSocketDTO;
import com.zgy.translate.global.GlobalConstants;
import com.zgy.translate.global.GlobalGattAttributes;
import com.zgy.translate.global.GlobalInit;
import com.zgy.translate.global.GlobalParams;
import com.zgy.translate.managers.inst.inter.BluetoothProfileManagerInterface;
import com.zgy.translate.utils.BluetoothRecorder;
import com.zgy.translate.utils.ConfigUtil;
import com.zgy.translate.utils.RedirectUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouguangyue on 2017/12/14.
 */

public class BluetoothProfileManager implements BluetoothProfile.ServiceListener{

    private static final int SOCKET = 0;
    private static final int GATT = 1;

    private BluetoothManager bluetoothManager;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothHeadset mBluetoothHeadset;
    private BluetoothA2dp mBluetoothA2dp;
    private BluetoothHealth mBluetoothHealth;
    private BluetoothGatt mBluetoothGatt;
    private BluetoothProfile mBluetoothProfile;
    private int pro;
    private Context mContext;
    private BluetoothProfileManagerInterface managerInterface;

    public BluetoothProfileManager(Context context,BluetoothProfileManagerInterface managerInterface){
        mContext = context;
        this.managerInterface = managerInterface;
        bluetoothManager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();
    }

    /**获取蓝牙是否连接以及连接状态*/

    public void getBluetoothProfile(){

        int flag = -1;
        int a2dp = mBluetoothAdapter.getProfileConnectionState(BluetoothProfile.A2DP);
        //int headset = mBluetoothAdapter.getProfileConnectionState(BluetoothProfile.HEADSET);

        int gatt = mBluetoothAdapter.getProfileConnectionState(BluetoothProfile.GATT);
        int gatt_service = mBluetoothAdapter.getProfileConnectionState(BluetoothProfile.GATT_SERVER);

        if(BluetoothProfile.STATE_CONNECTING == a2dp){
            flag = a2dp;
        }else if(BluetoothProfile.STATE_CONNECTING == gatt){
            flag = gatt;
        }else if(BluetoothProfile.STATE_CONNECTED == a2dp){
            flag = a2dp;
        }else if(BluetoothProfile.STATE_CONNECTED == gatt){
            flag = gatt;
        }else if(a2dp == 0 && gatt == 0){
            flag = 0;
        }

        if(flag == 0){
            managerInterface.noProfile();
        }else if(flag == 1){
            //连接中
            managerInterface.deviceConning();
        }else if(flag != -1){
            mBluetoothAdapter.getProfileProxy(mContext, this, flag);
        }
    }

    @Override
    public void onServiceConnected(int profile, BluetoothProfile proxy) {
        if(profile == BluetoothProfile.HEADSET){
            pro = profile;
            mBluetoothProfile = proxy;

        }else if(profile == BluetoothProfile.A2DP){
            pro = profile;
            mBluetoothProfile = proxy;
            getConnectionDevice(SOCKET, proxy);

        }else if(profile == BluetoothProfile.HEALTH){
            pro = profile;
            mBluetoothProfile = proxy;

        }else if(profile == BluetoothProfile.GATT){
            pro = profile;
            mBluetoothProfile = proxy;
            getConnectionDevice(GATT, proxy);

        }
    }

    @Override
    public void onServiceDisconnected(int profile) {
        if(profile == BluetoothProfile.HEADSET){
            mBluetoothHeadset = null;

        }else if(profile == BluetoothProfile.A2DP){
            mBluetoothA2dp = null;
            managerInterface.bluetoothOff();
            Log.i("A2DP", "没有连接A2DP");
        }else if(profile == BluetoothProfile.HEALTH){
            mBluetoothHealth = null;
        }else if(profile == BluetoothProfile.GATT){
            mBluetoothGatt = null;
        }
    }

    private void getConnectionDevice(int flag, BluetoothProfile proxy){
        boolean fl = false;
        switch (flag){
            case SOCKET:
                mBluetoothA2dp = (BluetoothA2dp) proxy;
                for (BluetoothDevice device : mBluetoothA2dp.getConnectedDevices()){
                    /*if(device.getUuids() != null){
                        for (ParcelUuid uuid : device.getUuids()){
                            Log.i("mBluetoothA2dp--uuid", uuid.toString());
                            if(GlobalGattAttributes.DEVICE_SERVICE.equals(uuid.toString())){
                                fl = true;
                                break;
                            }
                        }
                    }*/
                    if(device.getName() != null && GlobalConstants.BLUETOOTH_A2DP.equals(device.getName())){
                        GlobalParams.BLUETOOTH_MAC = device.getAddress();
                        GlobalParams.BlUETOOTH_DEVICE = device;
                        fl = true;
                        break;
                    }
                }
                if(fl){
                    managerInterface.getA2DPProfileFinish(true);
                }else{
                    managerInterface.getA2DPProfileFinish(false);
                }
                break;
            case GATT:
                mBluetoothGatt = (BluetoothGatt) proxy;
                for (BluetoothDevice device : mBluetoothGatt.getConnectedDevices()){
                    if(device.getUuids() != null){
                        for (ParcelUuid uuid : device.getUuids()){
                            if(GlobalGattAttributes.DEVICE_SERVICE.equals(uuid.toString())){
                                fl = true;
                                break;
                            }
                        }
                    }
                }
                if(fl){
                    managerInterface.getBLEProfileFinish(mBluetoothGatt, true);
                }else{
                    managerInterface.getBLEProfileFinish(mBluetoothGatt, false);
                }
                break;
        }

    }

    public void closeProfileProxy(){
        if(mBluetoothProfile != null){
            mBluetoothAdapter.closeProfileProxy(pro, mBluetoothProfile);
            pro = 0;
            mBluetoothProfile = null;
        }
    }

    public void onMyDestroy(){
        bluetoothManager = null;
        mBluetoothAdapter = null;
    }
}
