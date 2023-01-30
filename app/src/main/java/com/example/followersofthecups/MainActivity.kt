package com.example.followersofthecups

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.followersofthecups.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {

            val userNameLogin = binding.usernameLogin.text.toString()
            val passwordLogin = binding.passwordLogin.text.toString()

            if (userNameLogin.isNotEmpty()){
                readData(userNameLogin, passwordLogin)
            }
            else{
                Toast.makeText(this,"please enter valid username",Toast.LENGTH_SHORT).show()
            }
        }

        binding.newAccountButton.setOnClickListener {
            val newUser = Intent(this, RegisterActivity::class.java)
            startActivity(newUser)

        }
    }
    private fun readData(userName: String, password: String){
        database = FirebaseDatabase.getInstance("https://followersapp-58cf7-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users")
        database.child(userName).get().addOnSuccessListener {

            if(it.exists() && password == it.child("password").value){
                val userId = it.child("userId").value.toString().toInt()

                val email = it.child("email").value
                val supreme = it.child("supreme").value
                val supremeRank = it.child("supremeRank").value
                val supremeStreak = it.child("supremeStreak").value

                val userProfile = User(userName, password, email.toString(),userId.toString().toInt(),supreme.toString().toBoolean(),supremeRank.toString().toInt(),supremeStreak.toString().toInt())

                Toast.makeText(this,"login successful", Toast.LENGTH_SHORT).show()

                val userLogin = Intent(this, homepage::class.java).apply {
                    putExtra("user", userProfile)
                }
                startActivity(userLogin)
            }
            else{
                Toast.makeText(this,"username or password incorrect", Toast.LENGTH_SHORT).show()
            }

        }.addOnFailureListener {

        }
    }
}

