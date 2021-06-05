package com.example.widget

import android.os.Bundle
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.content_background_logic.*

class BackgroundLogic : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_background_logic)
        setSupportActionBar(findViewById(R.id.toolbar))
           val textview:TextView
            textview=findViewById(R.id.textView)
        val textvalue=textView.text.toString()
        val intView:Int =Integer.valueOf(textvalue)


        //create an object of the class
        val number = MyWorker()
        number.valueDouble(intView).toString()
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        } 
    }
}