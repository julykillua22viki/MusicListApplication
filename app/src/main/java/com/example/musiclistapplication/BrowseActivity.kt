package com.example.musiclistapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.musiclistapplication.ui.main.MainFragment

class BrowseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_browse)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

}
