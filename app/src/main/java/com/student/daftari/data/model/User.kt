package com.student.daftari.data.model

import java.io.Serializable

class User:Serializable {
    var name:String = ""
    var userName:String = ""
    var birthday : String = ""
    var phoneNumber : String = ""
    var education : String = ""
    var course : String = ""
    var fakultet : String = ""
    var email:String = ""
    var uid:String=""
    var addTime:Long=0
    var password:String=""
    constructor()

    constructor(name:String, birthday:String, phoneNumber:String, education:String, course:String, fakultet:String, email:String, uid: String, userName:String,addTime:Long,password:String){
        this.name = name
        this.birthday = birthday
        this.phoneNumber = phoneNumber
        this.education = education
        this.course = course
        this.fakultet = fakultet
        this.email = email
        this.uid=uid
        this.userName = userName
        this.addTime=addTime
        this.password = password
    }
}