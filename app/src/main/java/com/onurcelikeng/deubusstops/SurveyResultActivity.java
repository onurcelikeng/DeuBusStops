package com.onurcelikeng.deubusstops;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.onurcelikeng.deubusstops.Models.SurveyModel;

public class SurveyResultActivity extends AppCompatActivity {

    private TextView nameSurnameTextView;
    private TextView emailTextView;
    private TextView phoneTextView;
    private TextView genderTextView;
    private TextView birthdateTextView;
    private TextView stopTextView;
    private TextView pointTextView;
    private TextView opinionTextView;
    private Button okButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_result);

        nameSurnameTextView = (TextView) findViewById(R.id.nameSurnameTextView);
        emailTextView = (TextView) findViewById(R.id.emailTextView);
        phoneTextView = (TextView) findViewById(R.id.phoneTextView);
        genderTextView = (TextView) findViewById(R.id.genderTextView);
        birthdateTextView = (TextView) findViewById(R.id.birthdateTextView);
        stopTextView = (TextView) findViewById(R.id.stopTextView);
        pointTextView = (TextView) findViewById(R.id.pointTextView);
        opinionTextView = (TextView) findViewById(R.id.opinionTextView);
        okButton = (Button) findViewById(R.id.okButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);

        Intent intent = getIntent();
        SurveyModel survey = (SurveyModel) intent.getSerializableExtra("survey");

        nameSurnameTextView.setText(survey.getNameSurname());
        emailTextView.setText(survey.getEmail());
        phoneTextView.setText(survey.getPhone());
        genderTextView.setText(survey.getGender());
        birthdateTextView.setText(survey.getBirthdate());
        stopTextView.setText(survey.getBusStop());
        pointTextView.setText(String.valueOf(survey.getPoint()));
        opinionTextView.setText(survey.getOpinion());

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result","Ankete katıldığınız için teşekkürler.");
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
            }
        });
    }
}
