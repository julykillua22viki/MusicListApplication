package com.example.musiclistapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.musiclistapplication.ui.main.LinkFragment

class LinkActivity : AppCompatActivity() {

    companion object {
        val URL = "url"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.link_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LinkFragment.newInstance( intent.getStringExtra( URL ) ))
                .commitNow()
        }
    }

}