package com.example.multiprocess;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Enid on 2017/5/3.
 */

public class ProcessActivity2 extends AppCompatActivity {
    private static final String TAG = "ProcessActivity2";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process2);
        TextView textView = (TextView) findViewById(R.id.textview);
        StringBuilder sb = new StringBuilder();
        sb.append("当前线程id：" +  Thread.currentThread().getId() + "\n");
        sb.append("当前进程pid：" + android.os.Process.myPid() + "\n");
        sb.append("当前进程名称：" + Util.getCurProcessName(this) + "\n");
        textView.setText(sb.toString());
        Log.d(TAG,sb.toString());
        Log.d(TAG,"NUM：" + ProcessActivity1.NUM);
    }
}
