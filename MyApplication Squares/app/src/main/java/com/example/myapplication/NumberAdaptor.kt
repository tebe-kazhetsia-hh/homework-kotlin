package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class NumbersAdapter(private val numbers: List<Int>,
                     private val onItemClick: (Int) -> Unit) :
    RecyclerView.Adapter<NumbersAdapter.NumberViewHolder>() {

    class NumberViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val text: TextView = view.findViewById(R.id.number_text)

        fun bind(number: Int, onItemClick: (Int) -> Unit) {
            text.text = number.toString()
            val colorRes = if (number % 2 == 0) R.color.even else R.color.odd
            text.setBackgroundColor(ContextCompat.getColor(itemView.context, colorRes))
            itemView.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_number, parent, false)
        return NumberViewHolder(view)
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        holder.bind(numbers[position], onItemClick)
    }

    override fun getItemCount(): Int = numbers.size
}