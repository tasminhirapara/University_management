package com.example.myapplication3.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication3.R
import com.example.myapplication3.databinding.ActivityMainBinding
import com.example.myapplication3.databinding.ActivityMaindataBinding
import com.example.myapplication3.databinding.ActivityTimetableBinding
import com.islandparadise14.mintable.model.ScheduleDay
import com.islandparadise14.mintable.model.ScheduleDayOption
import com.islandparadise14.mintable.model.ScheduleEntity

class timetable : AppCompatActivity()
{    private lateinit var binding: ActivityTimetableBinding
     private val day = arrayOf("Mon", "Tue", "Wen", "Thu", "Fri","Sat","Sun")
    private val scheduleList: ArrayList<ScheduleEntity> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityTimetableBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val schedule = ScheduleEntity(32, "Database", "301", ScheduleDay.TUESDAY, "8:00", "10:30", "#73fcae68", "#000000")
        scheduleList.add(schedule)

    }
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        binding.table.initTable(day)
        binding.table.updateSchedules(scheduleList)
    }
}