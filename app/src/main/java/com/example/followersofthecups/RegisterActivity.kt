package com.example.followersofthecups

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.followersofthecups.databinding.ActivityMainBinding
import com.example.followersofthecups.databinding.ActivityRegisterBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.registerButton.setOnClickListener{
            val userName = binding.registerUsername.text.toString()
            val password = binding.registerPassword.text.toString()
            val email = binding.registerEmail.text.toString()
            val userId = 1
            val supreme = false
            val supremeRankString = binding.registerSupremeRank.text.toString()
            val supremeRank = supremeRankString.toInt()
            val supremeStreak = 0

            database = FirebaseDatabase.getInstance("https://followersapp-58cf7-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users")
            val user = User(userName,password, email, userId, supreme , supremeRank, supremeStreak)
            database.child(userName).setValue(user).addOnSuccessListener {

                Toast.makeText(this,"yay", Toast.LENGTH_SHORT).show()
                finish()
            }.addOnFailureListener{

                Toast.makeText(this,"Nay..", Toast.LENGTH_SHORT).show()
            }
        }
    }
}