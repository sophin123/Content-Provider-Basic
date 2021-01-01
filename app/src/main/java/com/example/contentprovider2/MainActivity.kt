package com.example.contentprovider2

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.contentprovider2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var dataBinding: ActivityMainBinding


    @SuppressLint("Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val rs = contentResolver.query(StudentProvider.CONTENT_URI, arrayOf(StudentProvider._ID, StudentProvider.name, StudentProvider.surname), null, null, StudentProvider.name)

        dataBinding.nextBtn.setOnClickListener {
            if (rs?.moveToNext()!!){
                dataBinding.editTextTextPersonName.setText(rs.getString(1))
                dataBinding.editTextTextPersonName2.setText(rs.getString(2))
            }
        }

        dataBinding.previousBtn.setOnClickListener {
            if (rs?.moveToPrevious()!!){
                dataBinding.editTextTextPersonName.setText(rs.getString(1))
                dataBinding.editTextTextPersonName2.setText(rs.getString(2))
            }
        }

        dataBinding.insertBtn.setOnClickListener {
            val cv = ContentValues()
            cv.put(StudentProvider.name, dataBinding.editTextTextPersonName.text.toString())
            cv.put(StudentProvider.surname, dataBinding.editTextTextPersonName2.text.toString())
            contentResolver.insert(StudentProvider.CONTENT_URI, cv)
            rs?.requery()

            toastMessage("Data inserted")

        }

        dataBinding.updateBtn.setOnClickListener {
            val cv = ContentValues()
            cv.put(StudentProvider.name, dataBinding.editTextTextPersonName.text.toString())
            cv.put(StudentProvider.surname, dataBinding.editTextTextPersonName2.text.toString())
            contentResolver.update(StudentProvider.CONTENT_URI, cv, "name = ?", arrayOf(dataBinding.editTextTextPersonName.text.toString()))
            contentResolver.update(StudentProvider.CONTENT_URI, cv, "surname = ?", arrayOf(dataBinding.editTextTextPersonName2.text.toString()))
            rs?.requery()
            toastMessage("Data Updated")

            clearText()
        }

        dataBinding.deleteBtn.setOnClickListener {
            contentResolver.delete(StudentProvider.CONTENT_URI, "name = ?", arrayOf(dataBinding.editTextTextPersonName.text.toString()))
            rs?.requery()
            toastMessage("Data Deleted")
            clearText()
        }


        dataBinding.clearBtn.setOnClickListener {
            dataBinding.editTextTextPersonName.setText("")
            dataBinding.editTextTextPersonName2.setText("")
            dataBinding.editTextTextPersonName.requestFocus()

            toastMessage("Data Cleared")
        }

    }

    fun toastMessage(message: String){
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    fun clearText(){
        dataBinding.editTextTextPersonName.setText("")
        dataBinding.editTextTextPersonName2.setText("")
    }
}