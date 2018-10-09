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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class HeartRatesActivity extends AppCompatActivity implements
        View.OnClickListener, DatePickerDialog.OnDateSetListener{

    private static final String TAG = HeartRatesActivity.class.getSimpleName();
    int yearOfBirth;
    private TextView mDateOfBirth;
    private EditText mFirstNameEditText, mLastNameEditText;
    private Button showDetailButton;
    String firstName, lastName;
    int year, month, dayOfMonth;

    private HeartRates mHeartRates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rates);

        showDetailButton = findViewById(R.id.show_heart_rate_button);
        mFirstNameEditText = findViewById(R.id.first_name_text_view);
        mLastNameEditText = findViewById(R.id.last_name_text_view);
        mDateOfBirth = findViewById(R.id.set_date_of_birth);
        View mSelectDOB = findViewById(R.id.date_of_birth_layout);

        mSelectDOB.setOnClickListener(this);
        showDetailButton.setOnClickListener(this);

//        getDetails();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.date_of_birth_layout:
                android.support.v4.app.DialogFragment dateFragment = new DatePickerFragment();
                dateFragment.show(getSupportFragmentManager(), "datePicker");
                break;
            case R.id.show_heart_rate_button:
                getDetails();
                break;
        }

    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        mDateOfBirth.setText(day + "/" + (month + 1) + "/" + year);
        yearOfBirth = year;

    }

    public void getDetails(){
        firstName = mFirstNameEditText.getText().toString();
        lastName = mLastNameEditText.getText().toString();
        mHeartRates = new HeartRates(firstName, lastName, LocalDate.of(year, month + 1, dayOfMonth + 1));
        LocalDate todayDate = LocalDate.now();
        int nowYear = todayDate.getYear();
        int age = nowYear - yearOfBirth;
        Toast.makeText(this, mHeartRates.getFirstName() + mHeartRates.getLastName() + age, Toast.LENGTH_SHORT).show();
    }


}
