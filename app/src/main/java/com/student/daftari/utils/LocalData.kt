package com.student.daftari.utils

import android.annotation.SuppressLint
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.student.daftari.data.model.LessonItem

object LocalData {
    @SuppressLint("StaticFieldLeak")
    var googleSignInClient: GoogleSignInClient? = null
    const val REQUEST_CODE = 0
    fun loadLessonsData(): ArrayList<LessonItem> {
        val array = ArrayList<LessonItem>()
        array.add(
            LessonItem(
                "Tarix",
                "https://user-images.githubusercontent.com/108933534/235162452-a7b3f27b-da84-429f-b703-c1cd2653ad4f.png",
                12
            )
        )

        array.add(
            LessonItem(
                "Tarix",
                "https://user-images.githubusercontent.com/108933534/235166526-fefceacc-3f65-4c51-b03d-e6658670a9ce.png",
                12
            )
        )
        array.add(
            LessonItem(
                "Ingiliz Tili",
                "https://user-images.githubusercontent.com/108933534/235165326-9afa9dc0-c7ed-4d45-a988-82423e55e5a4.png",
                12
            )
        )

        array.add(
            LessonItem(
                "Iqtisodiyot",
                "https://user-images.githubusercontent.com/108933534/235165400-24c77de3-6c74-4135-9789-d25b6ea20b6f.png",
                12
            )
        )

        array.add(
            LessonItem(
                "Ona tili",
                "https://user-images.githubusercontent.com/108933534/235165584-9a8671c9-b7c6-4067-81a6-a763d7aed2b9.png",
                12
            )
        )

        array.add(
            LessonItem(
                "Adabiyot",
                "https://user-images.githubusercontent.com/108933534/235165675-69d81bb3-db07-4ee0-8dc6-19f6724869e6.png",
                12
            )
        )
        return array
    }
}