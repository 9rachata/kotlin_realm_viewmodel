package com.example.myapplication.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import com.example.myapplication.R
import com.example.myapplication.model.Student
import kotlinx.android.synthetic.main.realm_layout.view.*

class RealM(internal var context : Context, internal var data: List<Student>) : RecyclerView.Adapter<RealM.MyViewHolder>(){


    internal var filterListResult : List<Student>

    init {
        this.filterListResult = data;
    }

    fun filterList(filteredCourseList: ArrayList<Student>) {
        this.filterListResult = filteredCourseList;
        notifyDataSetChanged();
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.realm_layout , p0 , false);

        return MyViewHolder(view);
    }

    override fun getItemCount(): Int {
        return filterListResult.size;
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        val std = this.filterListResult[p1];
        p0.setData(std , p1);
    }

    inner class MyViewHolder(item : View) : RecyclerView.ViewHolder(item){
        fun setData(student: Student ,  pos : Int){
            if(pos %2 == 1) {
                itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));

            }
            else {
                itemView.setBackgroundColor(Color.parseColor("#ffcc80"));
            }
            itemView.txtID.text = student.stdId.toString();
            itemView.txtDetail.text = student.firstName + " " +student.lastName + " Age " + student.age
        }
    }
}