package com.example.multiprocess;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Enid on 2017/5/3.
 */

public class WorkService1 extends Service {
    public static final String  TAG = "WorkService1";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"onBind ");
        Log.d(TAG,"NUM: " + ProcessActivity1.NUM);
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate ");
        Log.d(TAG,"NUM: " + ProcessActivity1.NUM);
    }
}
