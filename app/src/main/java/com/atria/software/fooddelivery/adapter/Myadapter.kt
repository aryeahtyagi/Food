package com.atria.software.fooddelivery.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.atria.software.fooddelivery.MainFragment
import com.atria.software.fooddelivery.R
import com.atria.software.fooddelivery.data.User
import com.bumptech.glide.Glide


class Myadapter(val context: MainFragment, private val userList: ArrayList<User>): RecyclerView.Adapter<Myadapter.MyViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{

        fun onItemClick(position: Int)
    }


    fun setOnItemClickListener(listener: onItemClickListener){
        mListener=listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myadapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.homeitem,parent, false)
        return MyViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: Myadapter.MyViewHolder, position: Int) {
        val user: User=userList[position]
        holder.hotelname.text =user.hotelname
        holder.category.text =user.category
        Glide.with(context)
            .load(user.profileimage)
            .into(holder.imageurl)
    }

    override fun getItemCount(): Int {
    return userList.size
    }

    public class MyViewHolder(itemView: View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView){
        val imageurl: ImageView =itemView.findViewById(R.id.imageurl)
        val hotelname : TextView= itemView.findViewById(R.id.hotelname)
        val category : TextView= itemView.findViewById(R.id.category)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }
}