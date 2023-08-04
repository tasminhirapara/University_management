package com.example.myapplication3.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication3.Model.Exammodel
import com.example.myapplication3.Model.maincontentModel
import com.example.myapplication3.R

class ExameAdapter(var context: Context, var list: MutableList<Exammodel>) : RecyclerView.Adapter<MyView4>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView4
    {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.maincontentdesign,parent,false)
        return MyView4(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyView4, position: Int) {
        holder.image.setImageResource(list.get(position).image)
        holder.text1.setText(list.get(position).name)
    }
}

class MyView4 (itemview: View) :RecyclerView.ViewHolder(itemview)
{
    var image: ImageView
    var text1: TextView


    init
    {

        image = itemview.findViewById(R.id.img)
        text1 = itemview.findViewById(R.id.txt1)
    }

}
