package com.example.mymuseum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.ActionBar
import com.example.mymuseum.LogIn.ActivityLogIn
import com.example.mymuseum.Museu.MuseuMainActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var mAuth : FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser

        Handler().postDelayed({
            if(user != null){
                val museuIntent = Intent(this, MuseuMainActivity::class.java)
                startActivity(museuIntent)
                finish()
            }
            else{
                val logInIntent = Intent(this, ActivityLogIn::class.java)
                startActivity(logInIntent)
                finish()
            }

        }, 2000)

    }
}