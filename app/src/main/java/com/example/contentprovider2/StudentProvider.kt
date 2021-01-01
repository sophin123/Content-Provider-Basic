package com.example.contentprovider2

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri

class StudentProvider: ContentProvider() {

    companion object{
        val PROVIDER_NAME = "com.example.contentprovider2/StudentProvider"
        val URL = "content://$PROVIDER_NAME/$DATABASE_TABLE"
        val CONTENT_URI = Uri.parse(URL)

        val _ID = "_id"
        val name = "NAME"
        val surname = "SURNAME"
    }

    lateinit var db: SQLiteDatabase


    override fun onCreate(): Boolean {
        val helper = context?.let { SqliteHelper(it) }
        db = helper?.writableDatabase!!
        return true

    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        db.insert(DATABASE_TABLE, null, values)
        context?.contentResolver?.notifyChange(uri, null)
        return uri
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        val count = db.update(DATABASE_TABLE, values, selection, selectionArgs)
        context?.contentResolver?.notifyChange(uri, null)
        return count

    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        val count = db.delete(DATABASE_TABLE, selection, selectionArgs)
        context?.contentResolver?.notifyChange(uri, null)
        return count
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return db.query(DATABASE_TABLE, projection, selection, selectionArgs, null, null, sortOrder)
    }

    override fun getType(uri: Uri): String? {
        return "vnd.android.cursor.dir/vnd.example.$DATABASE_TABLE"
    }


}