package com.example.learnapps.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.learnapps.R
import com.example.learnapps.RoomNotesActivity
import com.example.learnapps.adapter.NoteAdapter
import com.example.learnapps.databinding.FragmentNoteBinding
import com.example.learnapps.model.Note
import com.example.learnapps.viewmodel.NoteViewModel


class NoteFragment : Fragment(R.layout.fragment_note), SearchView.OnQueryTextListener, MenuProvider {

    // Declare the binding variable
    private var noteBinding: FragmentNoteBinding? = null
    private val binding get() = noteBinding!!

    // Declare ViewModel
    private lateinit var notesViewModel : NoteViewModel
    private lateinit var noteAdapter: NoteAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       noteBinding = FragmentNoteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost = requireActivity()
        menuHost.addMenuProvider(this,viewLifecycleOwner, Lifecycle.State.RESUMED)

        notesViewModel = (activity as RoomNotesActivity).noteViewModel
        setupHomeRecycleView()

        binding.addNoteFab.setOnClickListener{
            it.findNavController().navigate(R.id.action_noteFragment_to_addNoteFragment)
        }
    }

    private fun updateUI(note: List<Note>?){
        if (note != null){
            if (note.isNotEmpty()){
                binding.emptyNotesImage.visibility = View.GONE
                binding.noteRecyclerView.visibility = View.VISIBLE
            }
            else{
                binding.emptyNotesImage.visibility = View.VISIBLE
                binding.noteRecyclerView.visibility = View.GONE
            }
        }
    }

    private fun setupHomeRecycleView(){
        noteAdapter = NoteAdapter()
        binding.noteRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = noteAdapter
        }

        activity.let {
            notesViewModel.getAllNotes().observe(viewLifecycleOwner){
                note->noteAdapter.differ.submitList(note)
                updateUI(note)
            }
        }
    }

    private fun searchNote(query: String?){
        val searchQuery = "%$query%"

        notesViewModel.searchNote(searchQuery).observe(this){
            list -> noteAdapter.differ.submitList(list)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if(newText != null){
            searchNote(newText)
        }
        return true
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.note_menu,menu)

        val menuSearch = menu.findItem(R.id.searchMenu).actionView as SearchView
        menuSearch.isSubmitButtonEnabled = false
        menuSearch.setOnQueryTextListener(this)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        noteBinding = null
    }

}