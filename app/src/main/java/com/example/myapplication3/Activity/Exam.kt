package com.example.myapplication3.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication3.Adapter.ExameAdapter
import com.example.myapplication3.Adapter.maincontentAdapter
import com.example.myapplication3.Model.Exammodel
import com.example.myapplication3.Model.maincontentModel
import com.example.myapplication3.R
import com.example.myapplication3.databinding.ActivityExamBinding
import com.example.myapplication3.databinding.ActivityMainBinding

class Exam : AppCompatActivity()
{
    private lateinit var binding: ActivityExamBinding
    lateinit var list3: MutableList<Exammodel>
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityExamBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        list3=ArrayList()
        var manager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.recycler.layoutManager = manager

        list3.add(Exammodel(R.drawable.exam1,"Exam Schedule"))
        list3.add(Exammodel(R.drawable.exam2,"CIE Marks"))
        list3.add(Exammodel(R.drawable.exame3,"Mid Marks"))
        list3.add(Exammodel(R.drawable.exam4,"Mid Result"))


        var myadapter = ExameAdapter(applicationContext,list3)
        binding.recycler.adapter = myadapter
    }
}