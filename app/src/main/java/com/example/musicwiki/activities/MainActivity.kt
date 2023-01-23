package com.example.musicwiki.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.example.musicwiki.R
import com.example.musicwiki.helper.Constants

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment()
    }


    private fun addFragment() {
        supportFragmentManager.commit {
            add(R.id.container, Constants.getFragmentClass(Constants.SELECT_GENRE), null)
            addToBackStack(Constants.SELECT_GENRE)
            setReorderingAllowed(true)
        }
    }
}