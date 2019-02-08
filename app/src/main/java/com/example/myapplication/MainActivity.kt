package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.util.Log
import com.example.myapplication.model.Student
import io.realm.Realm
import io.realm.RealmConfiguration

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val realMFragment = RealMFragment();

        val ft : FragmentTransaction  = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainLayout, realMFragment);
        ft.commit()


    }
}
