package com.example.bmicomputer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etHeight;
    private EditText etWeight;
    private Button btnCalculate;
    private TextView tvBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etHeight = findViewById(R.id.etHeight);
        etWeight = findViewById(R.id.etWeight);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvBMI = findViewById(R.id.tvBMI);

        btnCalculate.setOnClickListener(v -> {
            try {
                double h = Double.parseDouble(etHeight.getText().toString())/100d;
                double w = Double.parseDouble(etWeight.getText().toString())/100d;
                tvBMI.setText("BMI: " + String.format("%.2f", w / h / h));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        });
    }
}