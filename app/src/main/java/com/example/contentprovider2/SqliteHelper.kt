package com.example.contentprovider2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

const val DATABASE_NAME = "StudentDatabase"
const val DATABASE_TABLE = "StudentTable"
const val DATABASE_VERSION = 1


class SqliteHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        var create_table = "CREATE TABLE $DATABASE_TABLE (_id INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SURNAME TEXT)"
        db?.execSQL(create_table)
        db?.execSQL("INSERT INTO $DATABASE_TABLE(NAME, SURNAME) VALUES('SOPHIN', 'SHRESTHA')")
        db?.execSQL("INSERT INTO $DATABASE_TABLE(NAME, SURNAME) VALUES('BISHNU', 'SHRESTHA')")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}