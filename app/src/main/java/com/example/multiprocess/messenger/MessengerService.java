package com.example.multiprocess.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Enid on 2017/5/5.
 */

public class MessengerService extends Service {
    private static final String TAG = "MessengerService";
    class IncomingHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    //接受到的消息
                    Bundle data = msg.getData();
                    String stringMsg = data.getString("stringMsg");
                    Log.i(TAG, stringMsg);

                    //向客服端发送消息
                    Messenger clientMessenger = msg.replyTo;
                    Message message = new Message();
                    message.what = 0;
                    Bundle bundle = new Bundle();
                    bundle.putString("stringMsg","ok,am from server");
                    message.setData(bundle);
                    try {
                        clientMessenger.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
    Messenger mServerMessenger = new Messenger(new IncomingHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(getApplicationContext(),"binding",Toast.LENGTH_SHORT).show();
        return mServerMessenger.getBinder();
    }
}
