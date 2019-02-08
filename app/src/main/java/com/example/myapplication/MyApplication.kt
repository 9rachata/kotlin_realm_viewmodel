package com.example.myapplication

import android.app.Application
import io.realm.Realm
import io.realm.Realm.setDefaultConfiguration
import io.realm.RealmConfiguration



class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        val realmConfig = RealmConfiguration.Builder()
            .name("Student.realm")
            .build()
        Realm.setDefaultConfiguration(realmConfig)


    }
}