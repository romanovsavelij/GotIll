package com.example.sava.gotill.engine;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.example.sava.gotill.R;
import com.example.sava.gotill.add_medecine;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AlarmClock extends BroadcastReceiver {
    final public static String ONE_TIME="onetime";
    AlarmManager am;
    Context context;

    public AlarmClock() {};

    public AlarmClock(Context context) {
        this.context = context;
        am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("alarm", "ALARM!!!");
        Toast.makeText(context, "ALARM!!!", Toast.LENGTH_LONG).show();
        MediaPlayer ring = MediaPlayer.create(context, R.raw.ring);
        ring.start();
        Intent intent1 = new Intent(context, add_medecine.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);
    }

    public void cancelAlarm(PendingIntent pendingIntent) {
        am.cancel(pendingIntent);
    }


    public void setAlarm(GregorianCalendar time) {
        Intent intent = new Intent(context, AlarmClock.class);
        intent.putExtra(ONE_TIME, Boolean.TRUE);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        long triggerTime = time.getTimeInMillis();
        if (Build.VERSION.SDK_INT >= 23) {
            am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerTime, pi);
        } else if (Build.VERSION.SDK_INT >= 19) {
            am.setExact(AlarmManager.RTC_WAKEUP, triggerTime, pi);
        } else {
            am.set(AlarmManager.RTC_WAKEUP, triggerTime, pi);
        }
    }
}
