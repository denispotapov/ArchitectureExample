package com.example.architectureexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.note_item.view.*

class NoteAdapter : ListAdapter<Note, NoteAdapter.NoteHolder>(NoteDiffCallback()) {

   // private var notes = emptyList<Note>()
    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.NoteHolder {
        return NoteHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteAdapter.NoteHolder, position: Int) {
        val current = getItem(position)

        holder.textViewTitle.text = current.title
        holder.textViewDescription.text = current.description
        holder.textViewPriority.text = current.priority.toString()
    }

    fun getNoteAt(position: Int): Note {
        return getItem(position)
    }

    /*override fun getItemCount(): Int {
        return notes.size
    }*/

    inner class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textViewTitle: TextView = itemView.text_view_title
        val textViewDescription: TextView = itemView.text_view_description
        val textViewPriority: TextView = itemView.text_view_priority

       init {
           itemView.setOnClickListener {
               val position = adapterPosition
               if(listener !=null && position != RecyclerView.NO_POSITION) {
                   listener?.onItemClick(getItem(position))
               }
           }
       }
    }

    interface OnItemClickListener {
        fun onItemClick(note: Note)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}

class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.title == newItem.title && oldItem.description == newItem.description
                && oldItem.priority == newItem.priority

    }

}