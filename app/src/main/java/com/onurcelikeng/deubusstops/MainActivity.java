package com.onurcelikeng.deubusstops;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText _nameSurnameEditText;
    private EditText _emailEditText;
    private EditText _phoneEditText;
    private RadioGroup _genderRadioGroup;
    private CalendarView _birthdateCalendarView;
    private Spinner _stopSnipper;
    private SeekBar _pointSeekBar;
    private EditText _opinionEditText;
    private Button _sendButton;
    private String[] spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _nameSurnameEditText = (EditText) findViewById(R.id.nameSurnameEditText);
        _emailEditText = (EditText) findViewById(R.id.emailEditText);
        _phoneEditText = (EditText) findViewById(R.id.phoneEditText);
        _genderRadioGroup = (RadioGroup) findViewById(R.id.genderRadioGroup);
        _birthdateCalendarView = (CalendarView) findViewById(R.id.birthdateCalendarView);
        _stopSnipper = (Spinner) findViewById(R.id.stopSnipper);
        _pointSeekBar = (SeekBar) findViewById(R.id.pointSeekBar);
        _opinionEditText = (EditText) findViewById(R.id.opinionEditText);
        _sendButton = (Button) findViewById(R.id.sendButton);

        spinner = new String[]{
                "Mühendislik Durağı", "Depark Durağı", "İşletme Durağı", "Mimarlık Durağı"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner);
        _stopSnipper.setAdapter(adapter);

        _stopSnipper.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getBaseContext(), spinner[i], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        _sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Anketiniz gönderilmiştir..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}