package com.example.learnapps

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fragment)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set up button click listeners for News Fragment
        val btnNews = findViewById<Button>(R.id.btnNews)
        btnNews.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, NewsFragment()) // Replace with your NewsFragment
                .setReorderingAllowed(true)
                .addToBackStack("menuBackStack")
                .commit()
        }

        // Set up button click listeners for Sports Fragment
        val btnSports = findViewById<Button>(R.id.btnSports)
        btnSports.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, SportsFragment())
                .setReorderingAllowed(true)
                .addToBackStack("menuBackStack")
                .commit()
        }

        // Set up button click listeners for Science Fragment
        val btnScience = findViewById<Button>(R.id.btnScience)
        btnScience.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, SciencesFragment())
                .setReorderingAllowed(true)
                .addToBackStack("menuBackStack")
                .commit()
        }
    }
}