package com.example.sava.gotill;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;



public class add_medicines_a_day extends AppCompatActivity {

    MyClock first_alarm, last_alarm;

    void add_alarm_clock(int time, Integer ind, LinearLayout ll) {
        View alarmView = getLayoutInflater().inflate(R.layout.alarm_view, null);
        ll.addView(alarmView);
        TextView textView = alarmView.findViewById(R.id.time);
        textView.setTag("textView" + ind.toString());
        textView.setText("lol");
        SeekBar seekBar = alarmView.findViewById(R.id.seek_bar);
        seekBar.setMax(1440);
        seekBar.setProgress(time);
        seekBar.setTag("seekBar" + ind.toString());
    }

    void add_seek_bars(Integer alarms_count) {
        LinearLayout ll = findViewById(R.id.alarms);
        int start_time = first_alarm.toMillis(), end_time = last_alarm.toMillis();
        if (alarms_count == 1) {
            add_alarm_clock(end_time, 0, ll);
        }
        else {
            int period = (end_time - start_time) / (alarms_count - 1);
            for (Integer i = 0; i < alarms_count; ++i) {
                int time = start_time + i * period;
                add_alarm_clock(time, 0, ll);
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
            }
        });
    }

}
