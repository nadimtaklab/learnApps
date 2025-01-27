package com.example.learnapps

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learnapps.databinding.ActivityMainBinding
import com.example.learnapps.view.CalculatorActivity

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize the binding object
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding?.fragmentActivityButton?.setOnClickListener {startFragmentActivity()}
        binding?.RecycleViewActivityButton?.setOnClickListener {startRecycleViewActivity()}
        binding?.MVVMButton?.setOnClickListener { startMVVMActivity()}
        binding?.RoomNotesButton?.setOnClickListener { startRoomNotesActivity()}

    }



    private fun startFragmentActivity() {
        val intent = Intent(this, FragmentActivity::class.java)
        startActivity(intent)
    }

    private fun startRecycleViewActivity(){
        val intent = Intent(this, RecycleViewActivity::class.java)
        startActivity(intent)
    }

    private fun startMVVMActivity() {
        val intent = Intent(this, CalculatorActivity::class.java)
        startActivity(intent)
    }

    private fun startRoomNotesActivity() {
        val intent = Intent(this, RoomNotesActivity::class.java)
        startActivity(intent)
    }

}