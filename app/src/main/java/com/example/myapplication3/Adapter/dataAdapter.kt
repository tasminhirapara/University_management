package com.example.myapplication3.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication3.Model.dataModel
import com.example.myapplication3.R
import com.squareup.picasso.Picasso

class dataAdapter(var context : Context, var list: MutableList<dataModel>) : RecyclerView.Adapter<ViewHolder1>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder1
    {
        val view = LayoutInflater.from(context).inflate(R.layout.datadesign, parent, false)
        return ViewHolder1(view)
    }

    override fun getItemCount(): Int
    {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder1, position: Int)
    {
        Picasso.get().load(list.get(position).image).into(holder.imageView)
        holder.name.setText(list.get(position).name)
    }
}

class ViewHolder1( itemView: View) : RecyclerView.ViewHolder(itemView)
{
    var imageView = itemView.findViewById<ImageView>(R.id.img)
    var name = itemView.findViewById<TextView>(R.id.txt1)
}
