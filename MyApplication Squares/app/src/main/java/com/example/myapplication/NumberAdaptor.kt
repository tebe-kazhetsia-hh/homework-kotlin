package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class NumbersAdapter(private var count: Int) :
    RecyclerView.Adapter<NumbersAdapter.NumberViewHolder>() {

    class NumberViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val text: TextView = view.findViewById(R.id.number_text)

        fun bind(number: Int) {
            text.text = number.toString()
            val colorRes = if (number % 2 == 0) R.color.even else R.color.odd
            text.setBackgroundColor(ContextCompat.getColor(itemView.context, colorRes))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_number, parent, false)
        return NumberViewHolder(view)
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        holder.bind(position + 1)
    }

    override fun getItemCount(): Int = count

    fun addOne() {
        count++
        notifyItemInserted(count - 1)
    }
}