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

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class BMIActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = BMIActivity.class.getSimpleName();

    private LinearLayout mLinearLayout;
    private Button mPoundInchesButton, mKgMetersButton, mCalculateButton;
    private TextView mResultTextView;
    private EditText mPoundEditText, mInchesEditText, mKilogramEditText, mMetersEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        mLinearLayout = findViewById(R.id.bottom_details_linear_layout);
        setUpLinearOutline();

        mPoundInchesButton = findViewById(R.id.bmi_pound_inches_button);
        mPoundInchesButton.setOnClickListener(this);

        mKgMetersButton = findViewById(R.id.bmi_kg_meters_button);
        mKgMetersButton.setOnClickListener(this);

        mResultTextView = findViewById(R.id.result_text_view);

        mPoundEditText = findViewById(R.id.weight_in_pound_edit_text);
        mInchesEditText = findViewById(R.id.height_in_inches_edit_text);
        mKilogramEditText = findViewById(R.id.weight_in_kg_edit_text);
        mMetersEditText = findViewById(R.id.height_in_meters_edit_text);

        mCalculateButton = findViewById(R.id.show_result_button);
        mCalculateButton.setOnClickListener(this);

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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bmi_kg_meters_button:
                mKilogramEditText.setVisibility(View.VISIBLE);
                mMetersEditText.setVisibility(View.VISIBLE);
                mPoundEditText.setVisibility(View.GONE);
                mInchesEditText.setVisibility(View.GONE);
                break;
            case R.id.bmi_pound_inches_button:
                mPoundEditText.setVisibility(View.VISIBLE);
                mInchesEditText.setVisibility(View.VISIBLE);
                mKilogramEditText.setVisibility(View.GONE);
                mMetersEditText.setVisibility(View.GONE);
                break;
            case R.id.show_result_button:
                calculateBMI();
                break;
        }
    }

    private void calculateBMI(){
        String weight = "";
        String height = "";

        if (mPoundEditText.isShown() && mInchesEditText.isShown()){
            weight = mPoundEditText.getText().toString().trim();
            height = mInchesEditText.getText().toString().trim();
            Toast.makeText(this, "Pound and Inches should work!", Toast.LENGTH_SHORT).show();
        } else if (mKilogramEditText.isShown() && mMetersEditText.isShown()){
            weight = mKilogramEditText.getText().toString().trim();
            height = mMetersEditText.getText().toString().trim();
            double weightValue = Double.parseDouble(weight);
            double heightValue = Double.parseDouble(height);
            double result = (weightValue / (heightValue * heightValue));
            /*NumberFormat numberFormat = new DecimalFormat("#0.00");
            numberFormat.format(result);*/
            String resultShown = String.format("%.2f", result);
            mResultTextView.setText(getString(R.string.display_result) + resultShown);
            Toast.makeText(this, "Kilogram and Meters is shown!" + result, Toast.LENGTH_SHORT).show();
        }

    }
}
