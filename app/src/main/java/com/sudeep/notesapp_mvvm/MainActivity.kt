package com.sudeep.notesapp_mvvm

import android.app.ActivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sudeep.notesapp_mvvm.Adapter.INoteClick
import com.sudeep.notesapp_mvvm.Adapter.NotesAdapter
import com.sudeep.notesapp_mvvm.Entity.Note
import com.sudeep.notesapp_mvvm.ViewModel.NoteViewModel

class MainActivity : AppCompatActivity() ,INoteClick{

    lateinit var viewModel :NoteViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var noteEdt :EditText
    lateinit var done :Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
              done=findViewById(R.id.doneBtn)
        recyclerView=findViewById(R.id.rv)
        recyclerView.layoutManager =LinearLayoutManager(this)
        noteEdt =findViewById(R.id.noteEDt)
        val adapter=NotesAdapter(this,this)
        recyclerView.adapter=adapter

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)

       viewModel.allNotes.observe(this, Observer { list ->


           adapter.updateList(list)

       })

        done.setOnClickListener {

            var noteText:String =noteEdt.text.toString()
            if (noteText.isNotEmpty()){

                viewModel.insert(Note(noteText))
                noteEdt.text.clear()
                Toast.makeText(this,"Inserted $noteText",Toast.LENGTH_SHORT).show()

            }
        }


    }

    override fun onItemClicked(note: Note) {
         viewModel.deleteNote(note)
        Toast.makeText(this,"Deleted ${note.text}",Toast.LENGTH_SHORT).show()

    }




}