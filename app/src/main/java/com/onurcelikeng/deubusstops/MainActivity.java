package com.onurcelikeng.deubusstops;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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
import android.widget.TextView;
import android.widget.Toast;

import com.onurcelikeng.deubusstops.Helpers.DBHandler;
import com.onurcelikeng.deubusstops.Models.BusStopModel;
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
    private EditText _opinionEditText;
    private Button _sendButton;
    private RatingBar _pointRatingBar;
    private String currentDate;
    private TextView _stopName;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _nameSurnameEditText = (EditText) findViewById(R.id.nameSurnameEditText);
        _emailEditText = (EditText) findViewById(R.id.emailEditText);
        _phoneEditText = (EditText) findViewById(R.id.phoneEditText);
        _genderRadioGroup = (RadioGroup) findViewById(R.id.genderRadioGroup);
        _birthdateCalendarView = (CalendarView) findViewById(R.id.birthdateCalendarView);
        _pointRatingBar = (RatingBar) findViewById(R.id.pointRatingBar);
        _opinionEditText = (EditText) findViewById(R.id.opinionEditText);
        _sendButton = (Button) findViewById(R.id.sendButton);
        _stopName = (TextView) findViewById(R.id.stopName);

        Intent intent = getIntent();
        final String name = (String) intent.getStringExtra("name");
        id = (String) intent.getStringExtra("id");

        _stopName.setText(name);

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
                survey.setBusStop(name);
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
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle(result);

                DBHandler db = new DBHandler(getApplicationContext());
                BusStopModel model = db.getBusStop(Integer.parseInt(id));

                model.setVoteCount(model.getVoteCount() + 1);
                model.setTotalPoint(model.getTotalPoint() + _pointRatingBar.getNumStars());
                db.updateBusStop(model);
                BusStopActivity.stopList = db.getAllBusStops();

                alertDialog.setMessage("Durak Adı: " + BusStopActivity.stopList.get(Integer.parseInt(id)).getName() + "\nAnkete katılan kişi sayısı: " + model.getVoteCount() + "\nDurağın toplam puanı: " + model.getTotalPoint());
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Kapat",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "İşlem iptal edildi.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
