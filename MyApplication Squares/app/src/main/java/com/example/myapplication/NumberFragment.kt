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

    private var count = 0

    private lateinit var adapter: NumbersAdapter
    private lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        count = savedInstanceState?.getInt(KEY_COUNT)
            ?: resources.getInteger(R.integer.initial_count)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_numbers, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spanCount = resources.getInteger(R.integer.span_count)

        adapter = NumbersAdapter(count)
        recycler = view.findViewById(R.id.recycler)
        recycler.layoutManager = GridLayoutManager(requireContext(), spanCount)
        recycler.adapter = adapter

        view.findViewById<Button>(R.id.add_button).setOnClickListener {
            count++
            adapter.addOne()
            recycler.scrollToPosition(count - 1)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_COUNT, count)
    }

    companion object {
        private const val KEY_COUNT = "count"
        private const val INITIAL_COUNT = 100
    }
}