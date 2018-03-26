package com.example.sava.gotill;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sava.gotill.engine.MyClock;

import java.time.Clock;


public class add_medicines_a_day extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    MyClock first_alarm, last_alarm;
    LinearLayout ll;

    void add_alarm_clock(int time, Integer ind) {
        View alarmView = getLayoutInflater().inflate(R.layout.alarm_view, null);
        TextView textView = alarmView.findViewById(R.id.time);
        textView.setTag(ind.toString());
        textView.setText(new MyClock(time).getTime());
        SeekBar seekBar = alarmView.findViewById(R.id.seek_bar);
        seekBar.setOnSeekBarChangeListener(this);
        seekBar.setMax(1439);
        seekBar.setProgress(time);
        seekBar.setTag(ind.toString());
        ll.addView(alarmView);
    }

    void add_seek_bars(Integer alarms_count) {
        ll = findViewById(R.id.alarms);
        int start_time = first_alarm.toMillis(), end_time = last_alarm.toMillis();
        if (alarms_count == 1) {
            add_alarm_clock(end_time, 0);
        }
        else {
            int period = (end_time - start_time) / (alarms_count - 1);
            for (Integer i = 0; i < alarms_count; ++i) {
                int time = start_time + i * period;
                add_alarm_clock(time, i);
            }
        }
    }

    void setAlarmClockTimes(Intent intent) {
        for (int i = 0; i < ll.getChildCount(); ++i) {
            View view = ll.getChildAt(i);
            if (view instanceof LinearLayout) {
                TextView textView = (TextView) ((LinearLayout) view).getChildAt(0);
                intent.putExtra("time" + i, textView.getText());
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("here", "here");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicines_a_day);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle b = getIntent().getExtras();
        first_alarm = new MyClock(b.getInt("first_alarm_hour"), b.getInt("first_alarm_minute"));
        last_alarm = new MyClock(b.getInt("last_alarm_hour"), b.getInt("last_alarm_minute"));
        Integer alarms_count = b.getInt("alarms_count");
        Toast.makeText(this, alarms_count.toString(), Toast.LENGTH_SHORT).show();
        add_seek_bars(alarms_count);
        Button send_button = findViewById(R.id.ok);
        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(add_medicines_a_day.this, "SEND!!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                setAlarmClockTimes(intent);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        Log.d("oc", "created!");
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (!fromUser) {
            return;
        }
        String tag = (String) seekBar.getTag();
        for (int i = 0; i < ll.getChildCount(); ++i) {
            View view = ll.getChildAt(i);
            if (view instanceof LinearLayout) {
                TextView textView = (TextView) ((LinearLayout) view).getChildAt(0);
                if (tag == textView.getTag()) {
                    textView.setText(new MyClock(progress).getTime());
                }
            }
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
