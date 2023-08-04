package com.example.myapplication3.Activity

import android.content.Context
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.ImageView
import com.example.myapplication3.R
import com.example.myapplication3.databinding.ActivityMainBinding
import com.example.myapplication3.databinding.ActivityProfileBinding
import java.io.File

class profileActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityProfileBinding
    lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE)
        var enrollment=sharedPreferences.getString("enrollment","")
        var name=sharedPreferences.getString("name","")
        var phone=sharedPreferences.getString("phone","")
        var course=sharedPreferences.getString("course","")
        var registration=sharedPreferences.getString("registration","")
        var datebirth=sharedPreferences.getString("datebirth","")

        val fileName = "profile_image.jpg"
        val imageView: ImageView = findViewById(R.id.imageview)

        val imageFile = File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "ProfileImages/$fileName")

        if (imageFile.exists()) {
            val bitmap = BitmapFactory.decodeFile(imageFile.absolutePath)
            imageView.setImageBitmap(bitmap)
        } else {
            // If the file does not exist, you can show a placeholder image or handle the situation accordingly.
            // For example, you could use a default image or hide the ImageView.
            imageView.setImageResource(R.mipmap.ic_launcher)
        }
        binding.name.setText(name)
        binding.enrollment.setText(enrollment)
        binding.phone.setText(phone)
        binding.course.setText(course)
        binding.regisrtation.setText(registration)
        binding.dob.setText(datebirth)



    }
}