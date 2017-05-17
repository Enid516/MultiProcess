package com.example.multiprocess.messenger;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.multiprocess.R;

/**
 * Created by Enid on 2017/5/5.
 */

public class MessengerActivity extends AppCompatActivity {
    private static final String TAG = "MessengerActivity";

    private Messenger mServerMessenger;

    private boolean mBound;

    private Handler mClientHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    //接受到的消息
                    Bundle data = msg.getData();
                    String stringMsg = data.getString("stringMsg");
                    Log.i(TAG,stringMsg);
            }
        }
    };

    private Messenger mClientMessenger = new Messenger(mClientHandler);

    private ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mServerMessenger = new Messenger(service);
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mServerMessenger = null;
            mBound = false;
        }
    };

    public void sayHello() {
        if (!mBound) {
            return;
        }
        Message msg = new Message();
        msg.what = 0;
        Bundle data = new Bundle();
        data.putString("stringMsg","hello server,am from client");
        msg.setData(data);
        msg.replyTo = mClientMessenger;
        try {
            mServerMessenger.send(msg);//向服务器发送消息
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onClick(View view) {
        sayHello();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(MessengerActivity.this, MessengerService.class);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            unbindService(mServiceConnection);
            mBound = false;
        }
    }
}
