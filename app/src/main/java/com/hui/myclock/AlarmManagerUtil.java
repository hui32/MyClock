package com.hui.myclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

/**
 * Created by liuhui on 16/8/4.
 */
public class AlarmManagerUtil {

    /**
     * @param context
     * */
    public static void setAlarmTime(Context context,long timeInMillis,Intent intent){
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent sender = PendingIntent.getBroadcast(
                context,
                0,
                intent,PendingIntent.FLAG_CANCEL_CURRENT);

        int interval = (int) intent.getLongExtra("intervalMillis",0);
        //manager.cancel(pendingIntent);

        //使用AlarmManger的setRepeating方法设置定期执行的时间间隔（seconds秒）和需要执行的Service
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            am.setWindow(AlarmManager.RTC_WAKEUP,timeInMillis,0,sender);
        }else {
            am.set(AlarmManager.RTC_WAKEUP,timeInMillis,sender);
        }
    }

    public static void startAlarm(Context mContext,Class<?> cls,long seconds,String action){
        AlarmManager am = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(mContext,cls);
        intent.setAction(action);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //触发服务的起始时间163983203
        //long triggerAtTime = SystemClock.elapsedRealtime();
        long triggerAtTime = System.currentTimeMillis();
        //manager.cancel(pendingIntent);
        //使用AlarmManger的setRepeating方法设置定期执行的时间间隔（seconds秒）和需要执行的Service
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            am.setWindow(AlarmManager.RTC_WAKEUP,triggerAtTime+seconds*1000*60,0,pendingIntent);
        }else {
            am.set(AlarmManager.RTC_WAKEUP,triggerAtTime+seconds*6000*1000*60,pendingIntent);
        }
    }

}
