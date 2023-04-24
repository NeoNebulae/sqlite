package com.example.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegistrationActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var genderEditText: EditText
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        nameEditText = findViewById(R.id.edittext_name)
        ageEditText = findViewById(R.id.edittext_age)
        genderEditText = findViewById(R.id.edittext_gender)
        usernameEditText = findViewById(R.id.edittext_username)
        passwordEditText = findViewById(R.id.edittext_password)
        registerButton = findViewById(R.id.button_register)

        registerButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val age = ageEditText.text.toString().toIntOrNull()
            val gender = genderEditText.text.toString()
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (name.isEmpty() || age == null || gender.isEmpty() || username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val dbHelper = DBHelper(this)
            val userModel = UserModel(name = name, age = age, gender = gender, username = username, password = password)
            val success = dbHelper.insertUser(userModel)

            if (success) {
                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Registration failed, try again", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
