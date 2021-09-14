package com.light.notes.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.light.notes.model.AppNote
import com.light.notes.zavodov.databinding.NoteItemBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private lateinit var layoutInflater: LayoutInflater

    private var listNotes = emptyList<AppNote>()

    fun setList(list: List<AppNote>) {
        listNotes = list
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(NoteItemBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.nameNote.text = listNotes[position].name
        holder.binding.textNote.text = listNotes[position].text
    }

    override fun getItemCount(): Int = listNotes.size

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        holder.itemView.setOnClickListener {
            MainFragment.click(listNotes[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        holder.itemView.setOnClickListener(null)
        super.onViewDetachedFromWindow(holder)
    }


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        layoutInflater = LayoutInflater.from(recyclerView.context)
    }

    class ViewHolder(val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}