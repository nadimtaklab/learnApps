package com.example.learnapps

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.learnapps.database.NoteDatabase
import com.example.learnapps.databinding.ActivityMainBinding
import com.example.learnapps.databinding.ActivityRoomNotesBinding
import com.example.learnapps.repository.NoteRepository
import com.example.learnapps.viewmodel.NoteViewModel
import com.example.learnapps.viewmodel.NoteViewModelFactory

class RoomNotesActivity : AppCompatActivity() {

    lateinit var  noteViewModel: NoteViewModel
    private var binding: ActivityRoomNotesBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize the binding object
        binding = ActivityRoomNotesBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupViewModel()
    }

    private fun setupViewModel(){
        val noteRepository = NoteRepository(NoteDatabase(this))
        val viewModelProvidelFactory = NoteViewModelFactory(application,noteRepository)
        noteViewModel = ViewModelProvider(this,viewModelProvidelFactory).get(NoteViewModel::class.java)
    }
}