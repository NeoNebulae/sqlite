package com.example.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "User.db"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(UserModel.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ${UserModel.TABLE_NAME}")
        onCreate(db)
    }

    fun insertUser(userModel: UserModel): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(UserModel.COLUMN_NAME, userModel.name)
        values.put(UserModel.COLUMN_AGE, userModel.age)
        values.put(UserModel.COLUMN_GENDER, userModel.gender)
        values.put(UserModel.COLUMN_USERNAME, userModel.username)
        values.put(UserModel.COLUMN_PASSWORD, userModel.password)

        val result = db.insert(UserModel.TABLE_NAME, null, values)
        return result != -1L
    }

    fun getUser(username: String, password: String): UserModel? {
        val db = this.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM ${UserModel.TABLE_NAME} WHERE ${UserModel.COLUMN_USERNAME}=? AND ${UserModel.COLUMN_PASSWORD}=?",
            arrayOf(username, password)
        )

        if (cursor != null && cursor.moveToFirst()) {
            val idIndex = cursor.getColumnIndex(UserModel.COLUMN_ID)
            val nameIndex = cursor.getColumnIndex(UserModel.COLUMN_NAME)
            val ageIndex = cursor.getColumnIndex(UserModel.COLUMN_AGE)
            val genderIndex = cursor.getColumnIndex(UserModel.COLUMN_GENDER)
            val usernameIndex = cursor.getColumnIndex(UserModel.COLUMN_USERNAME)
            val passwordIndex = cursor.getColumnIndex(UserModel.COLUMN_PASSWORD)

            if (idIndex != -1 && nameIndex != -1 && ageIndex != -1 && genderIndex != -1 && usernameIndex != -1 && passwordIndex != -1) {
                val id = cursor.getInt(idIndex)
                val name = cursor.getString(nameIndex)
                val age = cursor.getInt(ageIndex)
                val gender = cursor.getString(genderIndex)
                val username = cursor.getString(usernameIndex)
                val password = cursor.getString(passwordIndex)
                return UserModel(id, name, age, gender, username, password)
            }
        }

        return null
    }

}
