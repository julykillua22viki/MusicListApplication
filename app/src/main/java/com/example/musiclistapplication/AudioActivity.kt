package com.example.musiclistapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.musiclistapplication.model.Audio
import com.example.musiclistapplication.ui.main.AudioFragment

class AudioActivity : AppCompatActivity() {

    companion object {
        val CHILDREN = "children"
        val TITLE = "title"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.audio_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container,
                    AudioFragment.newInstance(
                        intent.getParcelableArrayListExtra<Audio>( CHILDREN ),
                        intent.getStringExtra( TITLE ) ))
                .commitNow()
        }
    }

}