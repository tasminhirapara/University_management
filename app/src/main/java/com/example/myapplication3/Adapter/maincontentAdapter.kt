package com.example.myapplication3.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication3.Activity.Maindata
import com.example.myapplication3.Model.maincontentModel
import com.example.myapplication3.R

class maincontentAdapter(var context: Context, var list: MutableList<maincontentModel>) : RecyclerView.Adapter<MyView>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView
    {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.maincontentdesign,parent,false)
        return MyView(view)
    }

    override fun getItemCount(): Int
    {
        return list.size
    }

    override fun onBindViewHolder(holder: MyView, position: Int)
    {

        holder.image.setImageResource(list.get(position).image)
        holder.text1.setText(list.get(position).name)

        holder.itemView.setOnClickListener()
        {
            var i = Intent(context, Maindata::class.java)
            i.putExtra("position", position)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(i)
        }
    }
}

class MyView(itemview: View) :RecyclerView.ViewHolder(itemview)
{
    var image: ImageView
    var text1: TextView


    init
    {

        image = itemview.findViewById(R.id.img)
        text1 = itemview.findViewById(R.id.txt1)
    }

}
