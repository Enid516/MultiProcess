package com.example.multiprocess.aidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.multiprocess.IMyAidlInterface;
import com.example.multiprocess.R;

/**
 * Created by Enid on 2017/5/8.
 */

public class AIDLActivity extends AppCompatActivity {
    private static final String TAG = "AIDLActivity";
    private IMyAidlInterface mIMyAidlInterface;
    ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
             mIMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        bindService(new Intent(AIDLActivity.this,AIDLService.class),mServiceConnection,BIND_AUTO_CREATE);
    }

    public void onClick(View view) {
        if (mIMyAidlInterface != null) {
            try {
                String hh = mIMyAidlInterface.getName("hh");
                Log.i(TAG,hh);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
