package com.sudeep.notesapp_mvvm.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sudeep.notesapp_mvvm.Entity.Note
import com.sudeep.notesapp_mvvm.R

class NotesAdapter(private val context:Context, private val listner:INoteClick): RecyclerView.Adapter<viewHolder>() {
    val allNotes =ArrayList<Note>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {

      val view = viewHolder(LayoutInflater.from(context).inflate(R.layout.single_note,parent,false))

        view.deleteBtn.setOnClickListener {
         listner.onItemClicked(allNotes[view.adapterPosition])
        }

        return view


    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.text.text=currentNote.text

    }

    override fun getItemCount(): Int {
       return allNotes.size
    }
    fun updateList(newList: List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

}
class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
              val text: TextView =itemView.findViewById<TextView>(R.id.noteText)
                val deleteBtn:ImageView=itemView.findViewById(R.id.deleteBtn)

}
interface INoteClick{
    fun onItemClicked(note: Note)
}