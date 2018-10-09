package com.example.ibnahmad.healthcalculator;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class HeartRatesActivity extends AppCompatActivity implements
        View.OnClickListener, DatePickerDialog.OnDateSetListener{

    private static final String TAG = HeartRatesActivity.class.getSimpleName();
    private static int year0fBirth;
    private TextView mDateOfBirth;

    private HeartRates mHeartRates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rates);

        mHeartRates = new HeartRates();
        mDateOfBirth = findViewById(R.id.set_date_of_birth);
        View mSelectDOB = findViewById(R.id.date_of_birth_layout);

        mSelectDOB.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        android.support.v4.app.DialogFragment dateFragment = new DatePickerFragment();
        dateFragment.show(getSupportFragmentManager(), "datePicker");
    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        mDateOfBirth.setText(day + "/" + (month + 1) + "/" + year);
        year0fBirth = year;
    }
}
