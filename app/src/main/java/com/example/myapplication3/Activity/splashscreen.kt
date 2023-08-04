package com.example.myapplication3.Activity


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.myapplication3.R


class splashscreen : AppCompatActivity()
{
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        Handler().postDelayed(Runnable {

            sharedPreferences= getSharedPreferences("login", Context.MODE_PRIVATE)
            if (sharedPreferences.getBoolean("login",false))
            {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            else
            {
                startActivity(Intent(application, loginscreen::class.java))
            }

                                       },1000)
    }
}