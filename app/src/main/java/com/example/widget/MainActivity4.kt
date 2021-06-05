package com.example.widget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.ScrollView
import android.widget.Toast
import android.app.DatePickerDialog
import android.view.View
import kotlinx.android.synthetic.main.activity_main2.*
import java.util.Calendar;
class MainActivity4 : AppCompatActivity() {
    lateinit var datePicker:DatePicker

    lateinit var scrollView:ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        datePicker = findViewById(R.id.datepicker1)


    }

    }
