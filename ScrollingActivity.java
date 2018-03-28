package com.example.sava.gotill;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sava.gotill.engine.AlarmClock;
import com.example.sava.gotill.engine.Data;
import com.example.sava.gotill.engine.MyClock;
import com.example.sava.gotill.engine.MyCustomAdapter;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ScrollingActivity extends AppCompatActivity {
    AlarmClock alarmClock;
    RecyclerView recyclerView;
    MyCustomAdapter adapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = getApplicationContext();
        alarmClock = new AlarmClock(context);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScrollingActivity.this, add_medecine.class);
                startActivityForResult(intent, 1);
            }
        });
        fab.setImageResource(R.drawable.plus);


        recyclerView = findViewById(R.id.recycleView);
        adapter = new MyCustomAdapter(this, Data.getData(context));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        Log.d("count", data.getStringExtra("alarms_count"));
        Integer alarms_count = Integer.valueOf(data.getStringExtra("alarms_count"));
        GregorianCalendar curDate = (GregorianCalendar) new GregorianCalendar().getInstance();

        for (int i = 0; i < alarms_count; ++i) {
            String name = "time" + i, time = data.getStringExtra(name);
            GregorianCalendar date = curDate;
            date.set(GregorianCalendar.HOUR_OF_DAY, new MyClock(time).hours);
            date.set(GregorianCalendar.MINUTE, new MyClock(time).minutes);
            alarmClock.setAlarm(date);
            save("time" + i, time);
            Log.d("result", time);
        }
        String inf = data.getStringExtra("period");
        //alarmClock.setAlarm(time);
    }

    private void save(String s, String time) {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(s, time);
        editor.commit();
    }

    private String read(String s) {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getString(s, getString(R.string.default_value));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
