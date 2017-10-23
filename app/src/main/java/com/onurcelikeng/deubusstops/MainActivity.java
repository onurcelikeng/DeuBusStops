package com.onurcelikeng.deubusstops;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Toast;

import com.onurcelikeng.deubusstops.Models.SurveyModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private EditText _nameSurnameEditText;
    private EditText _emailEditText;
    private EditText _phoneEditText;
    private RadioGroup _genderRadioGroup;
    private RadioButton _genderRadioButton;
    private CalendarView _birthdateCalendarView;
    private Spinner _stopSnipper;
    private EditText _opinionEditText;
    private Button _sendButton;
    private RatingBar _pointRatingBar;
    private String[] spinner;
    private String selectedBusStop;
    private String currentDate;

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
        _pointRatingBar = (RatingBar) findViewById(R.id.pointRatingBar);
        _opinionEditText = (EditText) findViewById(R.id.opinionEditText);
        _sendButton = (Button) findViewById(R.id.sendButton);

        spinner = new String[]{
                "Durak1", "Durak2", "Durak3", "Durak4", "Durak5"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner);
        _stopSnipper.setAdapter(adapter);

        _stopSnipper.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedBusStop = spinner[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        _sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedId = _genderRadioGroup.getCheckedRadioButtonId();
                _genderRadioButton = (RadioButton) findViewById(selectedId);
                SurveyModel survey = new SurveyModel();

                survey.setNameSurname(_nameSurnameEditText.getText().toString());
                survey.setEmail(_emailEditText.getText().toString());
                survey.setPhone(_phoneEditText.getText().toString());
                survey.setGender(_genderRadioButton.getText().toString());
                survey.setBirthdate(currentDate);
                survey.setBusStop(selectedBusStop);
                survey.setPoint(_pointRatingBar.getNumStars());
                survey.setOpinion(_opinionEditText.getText().toString());

                Intent intent = new Intent(MainActivity.this, SurveyResultActivity.class);
                intent.putExtra("survey", survey);
                startActivityForResult(intent, 1);
            }
        });

        _birthdateCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                currentDate = String.valueOf(dayOfMonth) + "/" + String.valueOf(month) + "/" + String.valueOf(year);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra("result");
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "İşlem iptal edildi.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
