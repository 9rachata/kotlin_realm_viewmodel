package com.example.myapplication.uility

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.myapplication.model.Student
import io.realm.Realm

class RealMViewModel : ViewModel() {

    private var dataStudent : MutableLiveData<List<Student>>? = null;

    private val realm = Realm.getDefaultInstance();

    fun getResponseLinePay(): LiveData<List<Student>> {

        if(dataStudent == null){
            dataStudent = MutableLiveData<List<Student>>();

            insertStudent(null);
        }

        return dataStudent as MutableLiveData<List<Student>>;
    }



    fun insertStudent(std: Student?){

            realm.executeTransactionAsync({ bgRealm ->


                if(std != null){
                    var max : Number?  = bgRealm.where(Student::class.java).max("stdId")

                    if(max == null){
                        max = 1;
                    }else{
                        max = max.toInt()
                        max += 1
                    }


                    var dataToinsert = bgRealm.createObject(Student::class.java , max)
                    dataToinsert.firstName = std.firstName + " $max"
                    dataToinsert.lastName = std.lastName
                    dataToinsert.gender =  std.gender
                    dataToinsert.city = std.city
                    dataToinsert.age = std.age

                    bgRealm.insert(dataToinsert)

                }


            }, {

                val data : MutableList<Student> = ArrayList<Student>();
                val std = realm.where(Student::class.java).findAll() as MutableList<Student>;
                std.forEach {
                    data.add(it)
                }

                dataStudent!!.value = data;


            }, {

                Log.i("TAG ERORR " , it.message);
            })

    }

}