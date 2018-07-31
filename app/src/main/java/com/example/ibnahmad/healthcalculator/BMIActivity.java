package com.example.ibnahmad.healthcalculator;

import android.graphics.Outline;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BMIActivity extends AppCompatActivity {

    private LinearLayout mLinearLayout;
    private Button mPoundInchesButton;
    private Button mKgMetersButton;
    private TextView mResultTextView;
    private EditText mPoundEditText, mInchesEditText, mKilogramEditText, mMetersEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        mLinearLayout = findViewById(R.id.bottom_details_linear_layout);
        setUpLinearOutline();

        mPoundInchesButton = findViewById(R.id.bmi_kg_meters_button);
        mPoundInchesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BMIActivity.this, "Pound and Inches is clicked", Toast.LENGTH_SHORT).show();
            }
        });

        mKgMetersButton = findViewById(R.id.bmi_pound_inches_button);
        mKgMetersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BMIActivity.this, "Kilogram and Meters is clicked", Toast.LENGTH_SHORT).show();
            }
        });

        mResultTextView = findViewById(R.id.result_text_view);

        mPoundEditText = findViewById(R.id.weight_in_pound_edit_text);
        mInchesEditText = findViewById(R.id.height_in_inches_edit_text);
        mKilogramEditText = findViewById(R.id.weight_in_kg_edit_text);
        mMetersEditText = findViewById(R.id.height_in_meters_edit_text);


    }

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
            mLinearLayout.setOutlineProvider(viewOutlineProvider);
            mLinearLayout.setClipToOutline(true);
        }else {
            mLinearLayout.setOutlineProvider(null);
            mLinearLayout.setClipToOutline(false);
        }
    }
}
