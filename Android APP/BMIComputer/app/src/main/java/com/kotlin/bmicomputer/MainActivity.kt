package com.kotlin.bmicomputer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etHeight = findViewById<EditText>(R.id.etHeight)
        val etWeight = findViewById<EditText>(R.id.etWeight)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val tvBmi = findViewById<TextView>(R.id.tvBMI)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        btnCalculate.setOnClickListener{
            try {  // 取得EditText的字串，並轉成浮點數
                var h = etHeight.text.toString().toDouble()/100 // 公分轉公尺
                var w = etWeight.text.toString().toDouble()
                var bmi = w / h / h
                tvBmi.setText("BMI :" + String.format("%.2f", bmi))
                if(bmi > 27) {
                    tvResult.setText("體重狀況: 肥胖")
                } else if(bmi >= 24) {
                    tvResult.setText("體重狀況: 過重")
                } else if(bmi >= 18.5) {
                    tvResult.setText("體重狀況: 健康體重")
                } else {
                    tvResult.setText("體重狀況: 過輕")
                }
            } catch (e: NumberFormatException) {
                e.printStackTrace()  // 印出錯誤
            }
        }
    }
}