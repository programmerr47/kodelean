package com.github.programmerr47.kodelean

import android.os.Bundle
import com.github.programmerr47.kodelean.android.TestAndroidActivity
import com.github.programmerr47.kodelean.core.temp

class MainActivity : TestAndroidActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val t = temp()
    }
}
