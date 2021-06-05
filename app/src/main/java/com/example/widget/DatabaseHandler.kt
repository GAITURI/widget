package com.example.widget

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "FirstDatabase"
        private val TABLE_USERS = "FirstTable"
        private val KEY_ID = "Id"
        private val KEY_NAME = "userName"
        private val KEY_EMAIL = "userEmail"

    }


    //creating an sql lite database
    override fun onCreate(db: SQLiteDatabase?) {
        //create a variable reference to save table
        //defining our query
        val CREATE_FIRST_TABLE = ("CREATE TABLE " + TABLE_USERS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_EMAIL + " TEXT" + ")")
//EXECUTE OUR QUERY
        db?.execSQL(CREATE_FIRST_TABLE)
    }

    //NOTIFYING THE DB IN CASE OF A CHANGE
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS" + TABLE_USERS)
        onCreate(db)
    }

    //defining our methods
    fun addUsers(sqlListModel:sqlListModel): Long {
        val db = this.writableDatabase
//defining and placing our content
        val contentValues = ContentValues()
        //put data  to respective fields
        contentValues.put(KEY_ID, sqlListModel.userId)
        contentValues.put(KEY_NAME, sqlListModel.userName)
        contentValues.put(KEY_EMAIL, sqlListModel.userEmail)
        //inbsert this row to my table in database
        val success =db.insert(TABLE_USERS,null,contentValues)
        db.close()
        return success

    }
    //method to read data
    fun readData():List<sqlListModel>{
        //get resizable array for our data
        val userArray:ArrayList<sqlListModel> =ArrayList<sqlListModel>()
        //defining our select query
        val selectQuery="SELECT * FROM $TABLE_USERS"
        val db=this.readableDatabase
        var cursor:Cursor?= null
        try{
            cursor=db.rawQuery(selectQuery,null)
        }catch (e:SQLiteException){
            db.execSQL(selectQuery)
            return ArrayList()
        }
//        iterating our record in db
        var userId:Int
        var userName:String
        var userEmail:String
        if (cursor.moveToFirst()){
            do {
                userId= cursor.getInt(cursor.getColumnIndex("Id"))
                userName= cursor.getString(cursor.getColumnIndex("userName"))
                userEmail= cursor.getString(cursor.getColumnIndex("userEmail"))
               val person = sqlListModel(userId=userId,userName = userName,userEmail = userEmail)
                userArray.add(person)
            }while (cursor.moveToNext())
        }
        return userArray
    }
    //update method
    fun updateData(sqlListModel: sqlListModel):Int{
    //processing db
        val db= this.writableDatabase
        //content values
        val contentValues= ContentValues()
        contentValues.put(KEY_ID,sqlListModel.userId)
        contentValues.put(KEY_NAME,sqlListModel.userName)
        contentValues.put(KEY_EMAIL,sqlListModel.userEmail)

        //updating the row
        val sucess= db.update(TABLE_USERS,contentValues,"Id=" + sqlListModel.userId,null)
        //close the connection
        return sucess

    }
    fun deleteData(sqlListModel: sqlListModel):Int{
        val db= this.writableDatabase
        val contentValues= ContentValues()
        contentValues.put(KEY_ID,sqlListModel.userId)
        //delete process
        val success= db.delete(TABLE_USERS,"id="+ sqlListModel.userId,null)
        db.close()
        return success
    }




}