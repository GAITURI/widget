package com.example.widget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        radio_group.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener{group, checkedId ->
                val radio:RadioButton = findViewById(checkedId)
                Toast.makeText(applicationContext,"On checked change :" +" ${radio.text}",Toast.LENGTH_SHORT).show()
            }
        )

button.setOnClickListener{
    var id:Int = radio_group.checkedRadioButtonId
    if (id!=-1){
        val radio:RadioButton= findViewById(id)
        Toast.makeText(applicationContext,"Your town is :"+"${radio.text}",Toast.LENGTH_SHORT).show()


    }else{
        Toast.makeText(applicationContext,"Nothing selected",Toast.LENGTH_SHORT).show()
    }
}

    }

    fun radio_button_click(view: View) {
        val radio:RadioButton=findViewById(radio_group.checkedRadioButtonId)
        Toast.makeText(applicationContext,"On click:${radio.text}",Toast.LENGTH_SHORT).show()
    }
}