package com.hui.myclock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 重复定义闹钟时间
 * Created by liuhui on 16/7/28.
 */
public class AlarmClockReciver extends BroadcastReceiver{
    private String TAG = AlarmClockReciver.class.getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra(Constant.MSG);
        long intervalMillis = intent.getLongExtra(Constant.INTERVAL_MILLIS,0);
        int repeat_frequency = intent.getIntExtra(Constant.REPEAT_FREQUENCY,0);
        Log.e(TAG,"闹铃响啦~.......");
        //RingUtil.ringBell(context);
        AlarmManagerUtil.setAlarmTime(context,System.currentTimeMillis() + intervalMillis,intent);
        /*if (repeat_frequency!=0){
            Log.e(TAG,"闹铃响啦~.......");
            RingUtil.ringBell(context);
            //AlarmManagerUtil.setAlarmTime(context,System.currentTimeMillis() + intervalMillis,intent);
        }else{

        }*/
    }
}
