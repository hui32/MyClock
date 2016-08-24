package com.hui.myclock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Date;

public class MainActivity extends Activity {
    private static final int CLICK_REQUEST_CODE = 0;
    private long intervalMillis = 20*1000;
    public static String ACTION_FLAG = "ALARM_CLOCK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Date date = new Date();
        setRingTime(date,0);
    }

    public void setRingTime(Date ringTime,int repeatFrequency){
        Intent intent = new Intent(ACTION_FLAG);
        intent.putExtra(Constant.MSG,"msg....!!!!");
        intent.putExtra(Constant.INTERVAL_MILLIS,intervalMillis);
        intent.putExtra(Constant.REPEAT_FREQUENCY,0);
        AlarmManagerUtil.setAlarmTime(this,System.currentTimeMillis() + intervalMillis,intent);
    }
}
