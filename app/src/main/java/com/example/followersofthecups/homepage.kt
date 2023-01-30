package com.example.followersofthecups

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.followersofthecups.databinding.ActivityHomepageBinding
import com.google.firebase.database.DatabaseReference


class homepage : AppCompatActivity() {

    private lateinit var binding: ActivityHomepageBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userProfile = intent.getParcelableExtra<User>("user")

        if (userProfile != null) {
            binding.textView.text = userProfile.userName
        }

    }
}