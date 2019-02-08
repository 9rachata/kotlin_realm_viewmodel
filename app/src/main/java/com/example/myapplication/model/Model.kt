package com.example.myapplication.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Student() : RealmObject(){
    @PrimaryKey
    var stdId: Int? = null
    var firstName: String? = null
    var lastName: String? = null
    var age: Int? = null
    var gender: String? = null
    var city: String? = null
}