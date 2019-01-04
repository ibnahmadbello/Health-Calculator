package com.example.ibnahmad.healthcalculator;

import android.content.Context;
import android.graphics.Outline;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Objects;

public class BMIActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = BMIActivity.class.getSimpleName();

    private LinearLayout mLinearLayout;
    private Button mPoundInchesButton, mKgMetersButton, mCalculateButton;
    private TextView mResultTextView;
    private EditText mPoundEditText, mInchesEditText, mKilogramEditText, mMetersEditText;

    String weight = "";
    String height = "";
    double result = 0.0;

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
                result = 0.0;
                mKilogramEditText.setVisibility(View.VISIBLE);
                mMetersEditText.setVisibility(View.VISIBLE);
                mPoundEditText.setVisibility(View.GONE);
                mPoundEditText.setText("");
                mInchesEditText.setVisibility(View.GONE);
                mInchesEditText.setText("");
                mResultTextView.setText("");
                break;
            case R.id.bmi_pound_inches_button:
                result = 0.0;
                mPoundEditText.setVisibility(View.VISIBLE);
                mInchesEditText.setVisibility(View.VISIBLE);
                mKilogramEditText.setVisibility(View.GONE);
                mKilogramEditText.setText("");
                mMetersEditText.setVisibility(View.GONE);
                mMetersEditText.setText("");
                mResultTextView.setText("");
                break;
            case R.id.show_result_button:
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputMethodManager != null) {
                    inputMethodManager.hideSoftInputFromWindow(Objects.requireNonNull(this.getCurrentFocus()).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
                calculateBMI();
                break;
        }
    }

    private void calculateBMI(){
        if (mPoundEditText.isShown() && mInchesEditText.isShown()){
            if (mPoundEditText.length() == 0 || mInchesEditText.length() == 0){
                mResultTextView.setText(R.string.empty_values);
                return;
            }
            weight = mPoundEditText.getText().toString().trim();
            height = mInchesEditText.getText().toString().trim();
            BMIPoundInches(weight, height);
        }
        else if (mKilogramEditText.isShown() && mMetersEditText.isShown()){
            if (mKilogramEditText.length() == 0 || mMetersEditText.length() == 0){
                mResultTextView.setText(R.string.empty_values);
                return;
            }
            weight = mKilogramEditText.getText().toString().trim();
            height = mMetersEditText.getText().toString().trim();
            BMIKilogramMeters(weight, height);
        }

    }

    private void BMIPoundInches(String passWeight, String passHeight){
        double weightValue = Double.parseDouble(passWeight);
        double heightValue = Double.parseDouble(passHeight);
        result = ((weightValue * 703) / (heightValue * heightValue));
//        resultShown = String.format("%.2f", result);
        mResultTextView.setText(getResources().getString(R.string.display_result, result));
    }

    private void BMIKilogramMeters(String passWeight, String passHeight){
        double weightValue = Double.parseDouble(passWeight);
        double heightValue = Double.parseDouble(passHeight);
        result = (weightValue / (heightValue * heightValue));
            /*NumberFormat numberFormat = new DecimalFormat("#0.00");
            numberFormat.format(result);*/
//        resultShown = String.format("%.2f", result);
        mResultTextView.setText(getResources().getString(R.string.display_result, result));
    }
}
