package com.student.daftari.utils

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.kongzue.dialogx.dialogs.TipDialog
import com.kongzue.dialogx.dialogs.WaitDialog
import com.student.daftari.R

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    TipDialog.show("$message!", WaitDialog.TYPE.ERROR, 2000)
        .setBackgroundColorRes(R.color.kokka_oxshash)

}
fun Fragment.showToastSuccess(message: String, duration: Int = Toast.LENGTH_SHORT) {
    TipDialog.show("$message!", WaitDialog.TYPE.SUCCESS, 2000)
        .setBackgroundColorRes(R.color.kokka_oxshash)

}

fun Fragment.state(bool: Boolean) {
    if (bool) {
        WaitDialog.show("Please Wait!").setBackgroundColorRes(R.color.kokka_oxshash)
    } else {
        WaitDialog.dismiss()
    }
}
