package com.hui.myclock;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.RingtoneManager;

/**
 * Created by liuhui on 16/8/23.
 */
public class RingUtil {
    private static MediaPlayer mp;
    public static void ringBell(Context context){
        if (null == mp){
            mp = new MediaPlayer();
        }
        try {
            mp.setDataSource(context, RingtoneManager
                    .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
            mp.prepare();
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void closeRing(){
        if (mp!=null){
            mp.stop();
        }
        mp = null;
    }
}
