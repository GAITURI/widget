package com.example.widget

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class SqlModel(private val context: Activity, private val userId:Array<String>, private val userName:Array<String>, private val userEmail:Array<String>):
    ArrayAdapter<String>(context,R.layout.sql_list,userName)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater=context.layoutInflater
        val rowView= inflater.inflate(R.layout.sql_list,null,true)


        val textId:TextView= rowView.findViewById(R.id.userid)
        val textUserName:TextView= rowView.findViewById(R.id.userName)
        val textUserEmail:TextView= rowView.findViewById(R.id.userEmail)
        //view ids and set data according to positions

        textId.text ="ID: ${userId[position]}"
        textUserName.text ="ID: ${userName[position]}"
        textUserEmail.text ="ID: ${userEmail[position]}"

        return rowView
    }
}