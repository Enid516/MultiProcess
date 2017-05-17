package com.example.multiprocess.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.multiprocess.IMyAidlInterface;

/**
 * Created by Enid on 2017/5/8.
 */

public class AIDLService extends Service {
    IMyAidlInterface.Stub mStub = new IMyAidlInterface.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public String getName(String nickName) throws RemoteException {
            return "nickName: " + nickName;
        }

    };
    public IBinder onBind(Intent intent) {
        return mStub;
    }
}
