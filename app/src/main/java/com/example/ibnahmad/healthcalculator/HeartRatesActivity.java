package com.example.ibnahmad.healthcalculator;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Outline;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class HeartRatesActivity extends AppCompatActivity implements
        View.OnClickListener, DatePickerDialog.OnDateSetListener{

    private static final String TAG = HeartRatesActivity.class.getSimpleName();
    int yearOfBirth;
    private TextView mDateOfBirth, mFirstNameTextView, mLastNameTextView, mDOBTextView,
            maxHeartRateTextView, targetHeartRateTextView, ageTextView;
    private EditText mFirstNameEditText, mLastNameEditText;
    private Button showDetailButton, showHealthTips;
    private LinearLayout mResultLinearLayout, mDisclaimerLinearLayout;
    String firstName, lastName, DOB;
    public static final int MAX_HEART_RATE = 220;
    int year, month, dayOfMonth, maximumHeartRate;

    private HeartRates mHeartRates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rates);

        showDetailButton = findViewById(R.id.show_heart_rate_button);
        mFirstNameEditText = findViewById(R.id.first_name_text_view);
        mLastNameEditText = findViewById(R.id.last_name_text_view);
        mDateOfBirth = findViewById(R.id.set_date_of_birth);
        mResultLinearLayout = findViewById(R.id.result_layout);
        mFirstNameTextView = findViewById(R.id.show_first_name);
        mLastNameTextView = findViewById(R.id.show_last_name);
        mDOBTextView = findViewById(R.id.show_date_of_birth);
        maxHeartRateTextView = findViewById(R.id.show_maximum_heart_rate);
        targetHeartRateTextView = findViewById(R.id.show_target_heart_rate);
        ageTextView = findViewById(R.id.show_age);
        mDisclaimerLinearLayout = findViewById(R.id.bottom_disclaimer_linear_layout);
        showHealthTips = findViewById(R.id.show_health_tip);
        setUpLinearOutline();
        View mSelectDOB = findViewById(R.id.date_of_birth_layout);

        mSelectDOB.setOnClickListener(this);
        showDetailButton.setOnClickListener(this);
        showHealthTips.setOnClickListener(this);

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
            case R.id.show_health_tip:
                Intent intent = new Intent(this, HealthTipActivity.class);
                startActivity(intent);
        }

    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        mDateOfBirth.setText(day + "/" + (month + 1) + "/" + year);
        yearOfBirth = year;
        DOB = day + "-" + (month + 1) + "-" + year;
    }

    public void getDetails(){
        firstName = mFirstNameEditText.getText().toString();
        lastName = mLastNameEditText.getText().toString();
        mHeartRates = new HeartRates(firstName, lastName, LocalDate.of(year, month + 1, dayOfMonth + 1));
        LocalDate todayDate = LocalDate.now();
        int nowYear = todayDate.getYear();
        int age = nowYear - yearOfBirth;

        Toast.makeText(this, mHeartRates.getFirstName() + mHeartRates.getLastName() + age, Toast.LENGTH_SHORT).show();
        showResult(firstName, lastName, age);
    }

    public void showResult(String fName, String lName, int yOfBirth){
        mResultLinearLayout.setVisibility(View.VISIBLE);
        mFirstNameTextView.setText(fName);
        mLastNameTextView.setText(lName);
        ageTextView.setText(getResources().getString(R.string.years, yOfBirth));
//        ageTextView.setText(String.valueOf(yOfBirth) + R.string.years);
        maximumHeartRate = MAX_HEART_RATE - yOfBirth;
        int minHeartTargetRate = ((50 * maximumHeartRate) / 100);
        int maxHeartTargetRate = ((85 * maximumHeartRate) / 100);
        mDOBTextView.setText(DOB);
        maxHeartRateTextView.setText(getResources().getString(R.string.maximum, maximumHeartRate));
        targetHeartRateTextView.setText(getResources().getString(R.string.target_heart_rate, minHeartTargetRate, maxHeartTargetRate));
    }

    // Require API 21
    private void setUpLinearOutline(){
        ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                // You could read directly from the view's width/height
//                int size = getResources().getDimensionPixelSize();
                final int margin = Math.min(view.getWidth(), view.getHeight()) / 10;
                outline.setRoundRect(margin, margin, view.getWidth() - margin, view.getHeight() - margin, margin / 2);
            }
        };
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            mDisclaimerLinearLayout.setOutlineProvider(viewOutlineProvider);
            mDisclaimerLinearLayout.setClipToOutline(true);
        }else {
            mDisclaimerLinearLayout.setOutlineProvider(null);
            mDisclaimerLinearLayout.setClipToOutline(false);
        }
    }

}
