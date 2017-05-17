package com.example.multiprocess;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ProcessActivity1 extends AppCompatActivity {
    private static final String TAG = "ProcessActivity1";
    public static int NUM = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this,WorkService1.class);
        startService(intent);

        TextView textView = (TextView) findViewById(R.id.textview);
        StringBuilder sb = new StringBuilder();
        sb.append("当前线程id：" +  Thread.currentThread().getId() + "\n");
        sb.append("当前进程pid：" + android.os.Process.myPid() + "\n");
        sb.append("当前进程名称：" + Util.getCurProcessName(this) + "\n");
        textView.setText(sb.toString());
        Log.d(TAG,sb.toString());
        Log.d(TAG,"NUM：" + ProcessActivity1.NUM);
    }

    public void onClick(View view) {
        startActivity(new Intent(this,ProcessActivity2.class));
    }
}
