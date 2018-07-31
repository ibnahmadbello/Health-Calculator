package com.example.ibnahmad.healthcalculator;

import android.graphics.Outline;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.LinearLayout;

public class BMIActivity extends AppCompatActivity {

    private LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        mLinearLayout = findViewById(R.id.bottom_details_linear_layout);
        setUpLinearOutline();

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
