package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NumbersFragment : Fragment() {

    private var numbers = ArrayList<Int>()
    private var removed = ArrayList<Int>()
    private var nextNumber = 1
    private lateinit var adapter: NumbersAdapter
    private lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {

            numbers = savedInstanceState.getIntegerArrayList(KEY_NUMBERS) ?: ArrayList()
            removed = savedInstanceState.getIntegerArrayList(KEY_REMOVED) ?: ArrayList()
            nextNumber = savedInstanceState.getInt(KEY_NEXT_NUMBER, 1)
        } else {

            val initialCount = resources.getInteger(R.integer.initial_count)
            numbers = ArrayList((1..initialCount).toList())
            removed = ArrayList()
            nextNumber = initialCount + 1
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_numbers, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spanCount = resources.getInteger(R.integer.span_count)

        adapter = NumbersAdapter(numbers) { position -> removeAt(position) }
        recycler = view.findViewById(R.id.recycler)
        recycler.layoutManager = GridLayoutManager(requireContext(), spanCount)
        recycler.adapter = adapter

        view.findViewById<Button>(R.id.add_button).setOnClickListener {
            addNumber()
        }
    }

    //добавили 2 функции
    private fun removeAt(position: Int) {
        val number = numbers.removeAt(position)

        removed.add(number)
        adapter.notifyItemRemoved(position)
    }


    private fun addNumber() {

        val number = if (removed.isNotEmpty()) {
            removed.removeAt(removed.size - 1)
        } else {
            nextNumber++
        }
        numbers.add(number)
        adapter.notifyItemInserted(numbers.size - 1)
        recycler.scrollToPosition(numbers.size - 1)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntegerArrayList(KEY_NUMBERS, numbers)
        outState.putIntegerArrayList(KEY_REMOVED, removed)
        outState.putInt(KEY_NEXT_NUMBER, nextNumber)
    }

    companion object {
        private const val KEY_NUMBERS = "numbers"
        private const val KEY_REMOVED = "removed"
        private const val KEY_NEXT_NUMBER = "next_number"
    }
}