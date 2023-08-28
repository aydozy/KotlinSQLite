package com.aydanilozyurek.kotlinsqlite

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // SQLite should be in try-catch

        try {
            // database is created
            val myDB = this.openOrCreateDatabase("Musicians", Context.MODE_PRIVATE,null)

            // ID will be created automatically
            myDB.execSQL("CREATE TABLE IF NOT EXISTS musicians(id INTEGER PRIMARY KEY, name VARCHAR, age INT)")

            // Insert value to the database

            //myDatabase.execSQL("INSERT INTO musicians (name, age) VALUES ('James',50)")
            //myDatabase.execSQL("INSERT INTO musicians (name, age) VALUES ('Lars',60)")
            //myDatabase.execSQL("INSERT INTO musicians (name, age) VALUES ('Kirk',55)")

            //myDatabase.execSQL("UPDATE musicians SET age = 61 WHERE name = 'Lars'") --> change the lars's age
            //myDatabase.execSQL("UPDATE musicians SET name = 'Kirk Hammett' WHERE id = 3") --> change the id 3's name

            myDB.execSQL("DELETE FROM musicians WHERE name = 'Lars'") // --> delete lars


            //val cursor = myDatabase.rawQuery("SELECT * FROM musicians WHERE id = 3",null)

            //val cursor = myDatabase.rawQuery("SELECT * FROM musicians WHERE name LIKE 'K%'",null)


            // rawQuery --> pull the data
            val cursor = myDB.rawQuery("SELECT * FROM musicians",null)

            val nameIx = cursor.getColumnIndex("name")
            val ageIx = cursor.getColumnIndex("age")
            val idIx = cursor.getColumnIndex("id")

            while (cursor.moveToNext()) {
                println("Name: " + cursor.getString(nameIx))
                println("Age: " + cursor.getInt(ageIx))
                println("Id: " + cursor.getInt(idIx))
            }

            cursor.close()

        } catch(e : Exception){
           // when we come across any errors catch code block will be executed.
            e.printStackTrace()
        }




    }
}