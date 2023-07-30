package com.student.daftari.utils

import android.view.View
import com.student.daftari.utils.enums.ScreenEnum
import java.util.*

fun View.visible(){
    this.visibility =View.VISIBLE
}
fun View.invisible(){
    this.visibility=View.INVISIBLE
}

fun String.screenEnum(): ScreenEnum {
    return when (this) {
        "LOGIN" -> ScreenEnum.LOGIN
        else -> ScreenEnum.HOME
    }
}