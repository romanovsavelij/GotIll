package com.example.sava.gotill;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class add_medecine extends AppCompatActivity {

    private Integer alarms_count;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Intent intent = new Intent();
        Log.d("alarms", alarms_count.toString());
        intent.putExtra("alarms_count", alarms_count.toString());
        for (int i = 0; i < alarms_count; ++i) {
            String name = "time" + i;
            intent.putExtra(name, data.getStringExtra(name));
        }
        EditText editText = findViewById(R.id.medicine);
        String medicineName = editText.getText().toString();
        editText = findViewById(R.id.prescription);
        String prescription = editText.getText().toString();
        intent.putExtra("medicineName", medicineName);
        intent.putExtra("prescription", prescription);
        intent.putExtra("cnt1", ((EditText) findViewById(R.id.cnt1)).getText());
        intent.putExtra("cnt2", ((EditText) findViewById(R.id.cnt2)).getText());
        Spinner spinner = findViewById(R.id.period);
        intent.putExtra("period", spinner.getSelectedItem().toString());
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medecine);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Spinner spinner = findViewById(R.id.period);
        String[] data = {"день", "неделя", "месяц"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        spinner.setAdapter(adapter);

        Button add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent alarms_intent = new Intent(add_medecine.this, add_medicines_a_day.class);
                Bundle b = new Bundle();
                EditText tf = findViewById(R.id.cnt1);
                alarms_count = Integer.valueOf(tf.getText().toString());
                b.putInt("alarms_count", alarms_count);
                int first_alarm_hour = 10;
                int first_alarm_minute = 0;
                int last_alarm_hour = 11;
                int last_alarm_minute = 30;
                b.putInt("first_alarm_hour", first_alarm_hour);
                b.putInt("first_alarm_minute", first_alarm_minute);
                b.putInt("last_alarm_hour", last_alarm_hour);
                b.putInt("last_alarm_minute", last_alarm_minute);
                alarms_intent.putExtras(b);
                startActivityForResult(alarms_intent, 1);
            }
        });
    }

}
