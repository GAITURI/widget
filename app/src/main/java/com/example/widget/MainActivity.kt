package com.example.widget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.AutoText
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity () {
    //initialize the var to hold the widget
    private lateinit var autoText: AutoCompleteTextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//identifying the widget
   autoText= findViewById(R.id.auto_textview)
//get the array
        val towns=resources.getStringArray(R.array.Towns)
        //create an adapter for the array
        val arrayAdapter= ArrayAdapter(this,android.R.layout.simple_list_item_1,towns)
        autoText.setAdapter(arrayAdapter)


        val button
                = findViewById<Button>(R.id.Button)
        if (button != null) {
            button.setOnClickListener(View.OnClickListener {
                val enteredText =
                    getString(R.string.location) + " " + autoText.text
                Toast. makeText (this, enteredText, Toast.LENGTH_SHORT).show()
            })
        }
    }
}