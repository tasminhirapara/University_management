package com.example.myapplication3.Activity

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.GridView
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication3.Adapter.ImageSlider
import com.example.myapplication3.Adapter.maincontentAdapter
import com.example.myapplication3.Interface.Apiinterface
import com.example.myapplication3.Model.maincontentModel
import com.example.myapplication3.R
import com.example.myapplication3.databinding.ActivityMainBinding
import com.smarteist.autoimageslider.SliderView
import nl.psdcompany.duonavigationdrawer.views.DuoDrawerLayout
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle
import java.io.File


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var slidermap = HashMap<String,Int>()
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var sliderView: SliderView
    lateinit var list2: MutableList<maincontentModel>
    private lateinit var apiinterface: Apiinterface
    lateinit var sharedPreferences : SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?)
    {

        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE)
        var phone=sharedPreferences.getString("enrollment","")
        var name=sharedPreferences.getString("name","")

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//
//        var sharedPreferences = getSharedPreferences("tasmin", MODE_PRIVATE)
        toolbar = findViewById(R.id.toolbar)
        binding.profilename.setText(name)


//-------------------slider----------------------------------------------
        sliderView = findViewById(R.id.slider)
        setSupportActionBar(toolbar)
        // on below line creating variable for array list.
        var list = listOf(R.drawable.slider_1,R.drawable.slider_2,R.drawable.slider_3,R.drawable.slider_4,R.drawable.slider_5)
        // on below line initializing our adapter class by passing our list to it.
        val adapter = ImageSlider(list)
        // on below line setting auto cycle direction for slider view from left to right.
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        // on below line setting adapter for slider view.
        sliderView.setSliderAdapter(adapter);
        // on below line setting scroll time for slider view
        sliderView.setScrollTimeInSec(3);
        // on below line setting auto cycle for slider view.
        sliderView.setAutoCycle(true);
        // on below line setting start cycle for slider view.
        sliderView.startAutoCycle();


//-------------------slider----------------------------------------------

        val drawerLayout = findViewById<View>(R.id.drawer) as DuoDrawerLayout
        val drawerToggle = DuoDrawerToggle(
            this, drawerLayout, toolbar,
            nl.psdcompany.psd.duonavigationdrawer.R.string.navigation_drawer_open,
            nl.psdcompany.psd.duonavigationdrawer.R.string.navigation_drawer_close
        )
        drawerLayout.setDrawerListener(drawerToggle)
        drawerToggle.syncState()
//
//        val imageUrl = "https://tasmin123.000webhostapp.com/topsproject/photo/art1.png"
//        val imageView: ImageView = findViewById(R.id.imageview)
//        ProfileImageManager2.loadProfileImage(this, imageUrl, imageView)
//
//// Save the profile image to local storage
//        val fileName = "profile_image.jpg"
//        ProfileImageManager2.saveProfileImageLocally(this, imageUrl, fileName)

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

        //--------------------------------------------------------------------------------
        binding.imageview.setOnClickListener {
            drawerLayout.closeDrawer()
            startActivity(Intent(this,profileActivity::class.java))
        }
        binding.l1.setOnClickListener()
        {
            drawerLayout.closeDrawer()
        }
        binding.l2.setOnClickListener()
        {
            drawerLayout.closeDrawer()
            startActivity(Intent(this,timetable::class.java))
        }
        binding.l3.setOnClickListener()
        {
            drawerLayout.closeDrawer()
            startActivity(Intent(this,Exam::class.java))
        }
        binding.l4.setOnClickListener()
        {
            drawerLayout.closeDrawer()
            startActivity(Intent(this,Activity::class.java))
        }
        binding.l5.setOnClickListener()
        {
            drawerLayout.closeDrawer()
            startActivity(Intent(this,payment::class.java))
        }
        binding.l6.setOnClickListener {
            sharedPreferences.edit().clear().commit()
            startActivity(Intent(applicationContext, loginscreen::class.java))
        }


        //--------------------------------------------------------------------------------

        list2=ArrayList()
        var manager: RecyclerView.LayoutManager =GridLayoutManager(this,2)
        binding.recycler.layoutManager = manager

        list2.add(maincontentModel(R.drawable.previouspaper,"Previous Paper"))
        list2.add(maincontentModel(R.drawable.notes,"Notes"))
        list2.add(maincontentModel(R.drawable.freebook,"Free Book"))
        list2.add(maincontentModel(R.drawable.assignment,"Assignment"))
        list2.add(maincontentModel(R.drawable.internship,"Internship"))
        list2.add(maincontentModel(R.drawable.homework,"Home Work"))
        list2.add(maincontentModel(R.drawable.plan,"Lesson Plan"))
        list2.add(maincontentModel(R.drawable.elearning,"E-Learning"))
        list2.add(maincontentModel(R.drawable.holiday,"Holiday"))


        var myadapter = maincontentAdapter(applicationContext,list2)
        binding.recycler.adapter = myadapter


    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        var a = AlertDialog.Builder(this)
        a.setTitle("Are you sure you want to exit ?")
        a.setPositiveButton("YES") { dialog: DialogInterface, i: Int ->
            finishAffinity()
        }
        a.setNegativeButton("NO") { dialog: DialogInterface, i: Int ->
            dialog.cancel()
        }
        a.show()
    }
}

fun <E> List<E>.add(element: maincontentModel) {

}

