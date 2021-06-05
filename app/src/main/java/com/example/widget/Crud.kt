package com.example.widget

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_crud.*

class Crud : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud)


    }

    fun saveData(view: View) {
        //capture user data
        val id =edit1.text.toString()
        val name=edit2.text.toString()
        val email=edit3.text.toString()
        //instance of our database helper class
        val databaseHandler:DatabaseHandler= DatabaseHandler(this)
        //validation
        if(id.trim()!="" && name.trim()!="" &&email.trim()!=""){
          //if its not equal to null,write to my sqllite
            val status= databaseHandler.addUsers(sqlListModel(Integer.parseInt(id),name,email))
            if (status>-1){
         Toast.makeText(applicationContext,"Record saved",Toast.LENGTH_LONG).show()
            edit1.text?.clear()
            edit2.text?.clear()
            edit3.text?.clear()
            }
        }else{
            //if its null,notify the user
            Toast.makeText(applicationContext,"Please input data",Toast.LENGTH_LONG).show()
        }
    }
//    to read the data
    fun readData(view: View) {
    //creating an instance of our database handler
     val databaseHandler=DatabaseHandler(this)
    val viewData:List<sqlListModel> = databaseHandler.readData()
    //defining array variables to store each records details
    val useId= Array<String>(viewData.size){"0"}
    val useName= Array<String>(viewData.size){"null"}
    val useEmail= Array<String>(viewData.size){"null"}
    var index = 0
    for (e in viewData){
        useId[index]=e.userId.toString()
        useName[index]=e.userName
        useEmail[index]=e.userEmail
        index++
    }

    //creating a custom adapter
        val myAdapter= SqlModel(this,useId,useName,useEmail)
        //set the adapter to my listview
    list1.adapter=myAdapter
}

    fun deleteData(view: View) {
        //create an alertDialogue for taking user id
        val dialogBuilder=AlertDialog.Builder(this)
        val inflater= this.layoutInflater
        //attach our custom view to the pop up
        val dialogView=inflater.inflate(R.layout.delete_dialogue,null)
        dialogBuilder.setView(dialogView)
        
        //capture the editText
        val delete_Id=dialogView.findViewById<EditText>(R.id.deleteId)
        //customise our pop up :tittle and message
        dialogBuilder.setTitle("DeleteData")
        dialogBuilder.setMessage("Enter id to delete data")
        //set up our buttons
        dialogBuilder.setPositiveButton("Delete",DialogInterface.OnClickListener { dialog, which ->

        //set what happens when the positive button is clicked
        //capture user input
            val inputId=delete_Id.text.toString()
            //create an instance of the database class
            val databaseHandler= DatabaseHandler(this)
            //validate that the input variable actually has data in it
            if (inputId.trim()!=""){
                //here we will use our delete method
                val status= databaseHandler.deleteData(sqlListModel(Integer.valueOf(inputId),"","" ))
                if (status > -1){
                    Toast.makeText(applicationContext,"Record deleted successfully",Toast.LENGTH_LONG).show()
                }


            }else{
                Toast.makeText(applicationContext,"Please input data",Toast.LENGTH_LONG).show()
            }
        })


        dialogBuilder.setNegativeButton("Cancel",DialogInterface.OnClickListener { dialog, which ->
            //set what happens when negative button is clicked
            Toast.makeText(applicationContext,"Process cancelled",Toast.LENGTH_LONG).show()
            //dismiss pop up
            dialog.dismiss()

        })
        
        dialogBuilder.setNeutralButton("Help",DialogInterface.OnClickListener   { dialog, which ->
            Toast.makeText(applicationContext,"Input an id to delete",Toast.LENGTH_LONG).show()


        })
        //t
    val b=dialogBuilder.create()
        b.show()

    }
    fun updateData(view: View) {
        //create an alert dialogue
        val dialogBuilder=AlertDialog.Builder(this)
        val inflater= this.layoutInflater
        //attach our custom view to the pop up
        val dialogView=inflater.inflate(R.layout.update_dialogue,null)
        dialogBuilder.setView(dialogView)
        //reference to the views
        val update_id=dialogView.findViewById<EditText>(R.id.updateUserid)
        val update_name=dialogView.findViewById<EditText>(R.id.updateUserName)
        val update_email=dialogView.findViewById<EditText>(R.id.updateUserEmail)
        //customize the pop up
        dialogBuilder.setTitle("Update data")
        dialogBuilder.setMessage("Enter an id, to update specific record")

        dialogBuilder.setPositiveButton("Update data",DialogInterface.OnClickListener { dialog, which ->
            val  updateId=update_id.text.toString()
            val  updateName=update_name.text.toString()
            val  updateEmail=update_email.text.toString()
            //validate data
            if (updateId.trim() !=""  && updateName.trim() !="" && updateEmail.trim()!=""){
              //  update record
                //instance of the database handler
                val databaseHandler= DatabaseHandler(this)
                val status= databaseHandler.updateData(sqlListModel(Integer.valueOf(updateId),updateEmail,updateName))
                if (status>-1){
                    Toast.makeText(applicationContext,"Update successful",Toast.LENGTH_LONG).show()

                }else{
                    Toast.makeText(applicationContext,"Update failed",Toast.LENGTH_LONG).show()
                }

            }else{
                Toast.makeText(applicationContext,"Please input data",Toast.LENGTH_LONG).show()
            }

        })

        dialogBuilder.setNegativeButton("Cancel",DialogInterface.OnClickListener { dialog, which ->
            //set what happens when negative button is clicked
            Toast.makeText(applicationContext,"Process cancelled",Toast.LENGTH_LONG).show()
            //dismiss pop up
            dialog.dismiss()

        })

        dialogBuilder.setNeutralButton("Help",DialogInterface.OnClickListener   { dialog, which ->
            Toast.makeText(applicationContext,"Input an id to delete",Toast.LENGTH_LONG).show()


        })
        val b=dialogBuilder.create()
        b.show()


    }
}