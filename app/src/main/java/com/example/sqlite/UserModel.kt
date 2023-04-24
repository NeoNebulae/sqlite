package com.example.sqlite

class UserModel(var id: Int = -1, var name: String?, var age: Int?, var gender: String?, var username: String?, var password: String?) {

    companion object {
        const val TABLE_NAME = "users"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_AGE = "age"
        const val COLUMN_GENDER = "gender"
        const val COLUMN_USERNAME = "username"
        const val COLUMN_PASSWORD = "password"

        const val CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_NAME TEXT, $COLUMN_AGE INTEGER, $COLUMN_GENDER TEXT, $COLUMN_USERNAME TEXT, $COLUMN_PASSWORD TEXT)"
    }
}
