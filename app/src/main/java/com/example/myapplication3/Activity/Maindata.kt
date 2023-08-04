package com.example.myapplication3.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication3.Adapter.dataAdapter
import com.example.myapplication3.Interface.Apiinterface
import com.example.myapplication3.Model.dataModel
import com.example.myapplication3.client.Apiclient
import com.example.myapplication3.databinding.ActivityMaindataBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Maindata : AppCompatActivity()
{

    private lateinit var binding: ActivityMaindataBinding
    lateinit var list: MutableList<dataModel>
    lateinit var apiinterface : Apiinterface
    lateinit var call: Call<List<dataModel>>
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMaindataBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        list = ArrayList()
        var i = intent
        Toast.makeText(applicationContext,""+i.getIntExtra("position",111), Toast.LENGTH_LONG).show()
        var pos = i.getIntExtra("position",111)
        binding.recycler1.layoutManager = GridLayoutManager(this,2)

        apiinterface = Apiclient.getretofit()!!.create(Apiinterface::class.java)
        if(pos==0)
        {
            //previous paper
            call =  apiinterface.paperView()
        }
        if(pos==1)
        {
            // notes
            call=  apiinterface.notesView()
        }
        if(pos==2)
        {
            // free book
            call=  apiinterface.booksView()
        }
        if(pos==3)
        {
            //assignment
            call=  apiinterface.assignmnetView()
        }
        if(pos==4)
        {

            startActivity(Intent(this,internship::class.java))
        }
        if(pos==5)
        {

            startActivity(Intent(this,homework::class.java))
        }
        if(pos==6)
        {

            startActivity(Intent(this,lessonplan::class.java))
        }
        if(pos==7)
        {

            startActivity(Intent(this,elearning::class.java))
        }
        if(pos==8)
        {

            startActivity(Intent(this,holiday::class.java))
        }
        call.enqueue(object : Callback<List<dataModel>>
        {
            override fun onResponse(call: Call<List<dataModel>>, response: Response<List<dataModel>>, )
            {
                list = response.body() as MutableList<dataModel>

                var adapter = dataAdapter(applicationContext,list)
                binding.recycler1.adapter=adapter
            }

            override fun onFailure(call: Call<List<dataModel>>, t: Throwable)
            {
                Toast.makeText(applicationContext, "data loding failed", Toast.LENGTH_SHORT).show()
            }
        })

        // Load the profile image from the URL and display it using Glide

    }
}