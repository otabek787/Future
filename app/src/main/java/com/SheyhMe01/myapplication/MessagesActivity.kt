package com.SheyhMe01.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.SheyhMe01.myapplication.databinding.ActivityMainBinding
import com.SheyhMe01.myapplication.databinding.ActivityMessagesBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

class MessagesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMessagesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessagesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseAuth.getInstance().currentUser?.let { user ->
            binding.userId.text = user.displayName
            binding.textView2.text = user.email
            Picasso.get()
                .load(user.photoUrl)
                .placeholder(R.drawable.profile)
                .into(binding.circleImage)
        }

        binding.logOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            finish()
        }

    }
}