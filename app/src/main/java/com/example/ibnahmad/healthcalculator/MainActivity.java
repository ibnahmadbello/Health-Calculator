package com.example.ibnahmad.healthcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mBMITextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBMITextView = findViewById(R.id.bmi_text_view);
        mBMITextView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bmi_text_view:
                Intent startBMIActivity = new Intent(MainActivity.this, BMIActivity.class);
                startActivity(startBMIActivity);
        }
    }
}
