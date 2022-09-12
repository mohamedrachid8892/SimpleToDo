package com.example.simpletodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    val listOfTasks = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listOfTasks.add("Do laundry")
        listOfTasks.add("Go to the bank")

        // Lookup RecyclerView in the layout
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        // Create adapter passing in sample user data
        val adapter = TaskItemAdapter(listOfTasks)

        // Attach adapter to RecyclerView to populate items
        recyclerView.adapter = adapter

        // Set layout manager to position items
        recyclerView.layoutManager = LinearLayoutManager(this)

    }
}