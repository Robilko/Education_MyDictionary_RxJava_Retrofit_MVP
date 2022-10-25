package com.example.mydictionary.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mydictionary.R
import com.example.model.data.DataModel
import com.example.repository.utils.convertMeaningsTranslationToString

class MainAdapter(
    private var onListItemClick: (DataModel) -> Unit
) : RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {

    private var data: List<DataModel> = arrayListOf()

    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_main_recyclerview_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.findViewById<TextView>(R.id.header_textview_recycler_item).text = data.text
                itemView.findViewById<TextView>(R.id.description_textview_recycler_item).text =
                    convertMeaningsTranslationToString(data.meanings!!)
                //Вешаем слушатель
                itemView.setOnClickListener { openInNewWindow(data) }
            }
        }
    }
    // Передаём событие в MainActivity
    private fun openInNewWindow(listItemData: DataModel) {
        onListItemClick(listItemData)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

