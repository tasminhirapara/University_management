package com.example.myapplication3.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.finishAffinity
import com.example.myapplication3.Interface.Apiinterface
import com.example.myapplication3.Model.LoginModel
import com.example.myapplication3.R
import com.example.myapplication3.client.Apiclient
import com.example.myapplication3.databinding.ActivityLoginscreenBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class loginscreen : AppCompatActivity() {
    private lateinit var binding: ActivityLoginscreenBinding
    private lateinit var apiinterface: Apiinterface
    lateinit var sharedPreferences : SharedPreferences
    lateinit var list : MutableList<LoginModel>

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginscreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

         sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE)

        list = ArrayList()

        binding.btn1.setOnClickListener()
        {
            val enrollment = binding.ed1.text.toString()
            val password = binding.ed2.text.toString()

            if(enrollment.isEmpty() && enrollment.isEmpty())
            {
                binding.ed1.error = "Please Enter Name"
                binding.ed2.error = "Please Enter Password"
            }
            else if(enrollment.isEmpty())
            {
                binding.ed1.error = "Please Enter Name"
            }
            else if(enrollment.isEmpty())
            {
                binding.ed2.error = "Please Enter Password"
            }
            else
            {
                apiinterface = Apiclient.getretofit()!!.create(Apiinterface::class.java)

                val call: Call<List<LoginModel>> = apiinterface.logindata(enrollment, password)
                call.enqueue(object : Callback<List<LoginModel>>
                {
                    override fun onResponse(
                        call: Call<List<LoginModel>>,
                        response: Response<List<LoginModel>>, )
                    {
                        list = response.body() as MutableList<LoginModel>

                        val imageUrl = list[0].image
//                        val imageView: ImageView = findViewById(R.id.imageview)
//                        ProfileImageManager2.loadProfileImage(this@loginscreen, imageUrl, imageView)

// Save the profile image to local storage
                        val fileName = "profile_image.jpg"
                        ProfileImageManager2.saveProfileImageLocally(this@loginscreen, imageUrl, fileName)

                        Toast.makeText(applicationContext, "login successfull", Toast.LENGTH_LONG).show()
                        val i = Intent(applicationContext, MainActivity::class.java)
                        val edit: SharedPreferences.Editor = sharedPreferences.edit()
                        edit.putBoolean("login", true)
                        edit.putString("enrollment", list[0].enrollment)
//                        edit.putString("image", list[0].image)
                        edit.putString("registration", list[0].registration)
                        edit.putString("name", list[0].name)
                        edit.putString("datebirth", list[0].datebirth)
                        edit.putString("email", list[0].email)
                        edit.putString("phone", list[0].phone)
                        edit.putString("course", list[0].course)
                        edit.commit()
                        startActivity(i)

                    }

                    override fun onFailure(call: Call<List<LoginModel>>, t: Throwable)
                    {
                        Toast.makeText(applicationContext, "Login faild", Toast.LENGTH_LONG).show()
                    }
                })
            }

        }
    }

    @Deprecated("Deprecated in Java", ReplaceWith("finishAffinity()"))
    override fun onBackPressed()
    {
        finishAffinity()
    }


}

