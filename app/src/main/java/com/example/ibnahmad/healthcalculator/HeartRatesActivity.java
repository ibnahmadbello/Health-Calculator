package com.example.ibnahmad.healthcalculator;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class HeartRatesActivity extends AppCompatActivity implements
        View.OnClickListener{

    private static final String TAG = HeartRatesActivity.class.getSimpleName();
    private TextView mDateOfBirth;

    // Selected date, stored as a timestamp
    private long mDOB = Long.MAX_VALUE;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rates);

        mDateOfBirth = findViewById(R.id.set_date_of_birth);
        View mSelectDOB = findViewById(R.id.date_of_birth_layout);

        mSelectDOB.setOnClickListener(this);
//        updateDateDisplay();
    }

    @Override
    public void onClick(View view) {
        /*DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(getSupportFragmentManager(), TAG);*/
        Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                mDateOfBirth.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
            }
        }, mYear, mMonth, mDay);
        datePickerDialog.show();

    }

    /*public long getDateSelection(){
        return mDOB;
    }

    // Manage the selected date value
    public void setDateSelected(long dateSelected){
        mDOB = dateSelected;
        updateDateDisplay();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        // Set to the date selected
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);

        setDateSelected(calendar.getTimeInMillis());
    }

    private void updateDateDisplay(){
        if (getDateSelection() == Long.MAX_VALUE){
            mDateOfBirth.setText(R.string.date_of_birth_not_set);
        } else {
            CharSequence formatted = DateUtils.getRelativeTimeSpanString(this, mDOB);
            mDateOfBirth.setText(formatted);
        }
    }*/
}
