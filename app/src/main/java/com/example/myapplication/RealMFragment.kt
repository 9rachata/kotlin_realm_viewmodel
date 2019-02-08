package com.example.myapplication



import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.myapplication.adapter.RealM
import com.example.myapplication.model.Student
import com.example.myapplication.uility.RealMViewModel
import io.realm.RealmResults


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class RealMFragment : Fragment {


    var data : MutableList<Student> = ArrayList<Student>();

    lateinit var adapter : RealM

    constructor() : super()

    fun filter(text: String) {

        val filterResult: MutableList<Student> = ArrayList()

        val rows : ArrayList<Student> = data as ArrayList<Student>

        for (row in rows) {
            if (row.firstName!!.toLowerCase().contains(text.toLowerCase())) {
                filterResult.add(row)
            }
        }
        adapter.filterList(filterResult as ArrayList<Student>);
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val v : View = inflater.inflate(R.layout.fragment_real_m, container, false)


        val txtFirstName = v.findViewById(R.id.txtFirstName) as EditText
        val txtLastName = v.findViewById(R.id.txtLastName) as EditText
        val txtCity = v.findViewById(R.id.txtCity) as EditText
        val txtAge = v.findViewById(R.id.txtAge) as EditText
        val selectGender = v.findViewById(R.id.selectGender) as Spinner
        val btnInsert = v.findViewById(R.id.btnInsert) as Button

        val layoutManager = LinearLayoutManager(context);
        layoutManager.orientation = LinearLayoutManager.VERTICAL;


        val realmList = v.findViewById(R.id.recRealM) as RecyclerView
        realmList.layoutManager = layoutManager

        var model = ViewModelProviders.of(this.activity!!).get(RealMViewModel::class.java)

        model.getResponseLinePay().observe(this , Observer {

            data = (it as MutableList<Student>?)!!
            adapter = RealM(this!!.context!!, data);

            realmList.adapter = adapter

        })

        btnInsert.setOnClickListener {
            var std = Student();

            if(selectGender.selectedItemPosition != 0){
                std.firstName = txtFirstName.text.toString();
                std.lastName = txtLastName.text.toString();
                std.gender = selectGender.selectedItem.toString()
                std.city = txtCity.text.toString();
                std.age = txtAge.text.toString().toInt();

                model.insertStudent(std)

                Toast.makeText(context , "Insert Success" , Toast.LENGTH_SHORT).show()


                txtFirstName.setText("");
                txtLastName.setText("");
                txtCity.setText("");
                txtAge.setText("");
            }

        }

        val searchView = v.findViewById(R.id.search) as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                if (query != null) {
                    filter(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {


                if (newText != null) {
                    filter(newText)
                }
                return false
            }

        })

        return v;
    }
}

private fun <E> MutableList<E>.add(element: RealmResults<E>) {

}







