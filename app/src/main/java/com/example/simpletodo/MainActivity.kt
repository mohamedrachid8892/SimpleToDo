package com.example.simpletodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    val listOfTasks = mutableListOf<String>()
    lateinit var adapter : TaskItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val onLongClickListener = object : TaskItemAdapter.OnLongClickListener {
            override fun onItemLongClicked(position: Int) {
                // 1. Remove the item from the list
                listOfTasks.removeAt(position)

                // 2. Notify the adapter that our data set has changed
                adapter.notifyDataSetChanged()
            }
        }

        listOfTasks.add("Do laundry")
        listOfTasks.add("Go to the bank")

        // Lookup RecyclerView in the layout
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        // Create adapter passing in sample user data
        adapter = TaskItemAdapter(listOfTasks, onLongClickListener)

        // Attach adapter to RecyclerView to populate items
        recyclerView.adapter = adapter

        // Set layout manager to position items
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Set up the button and input field, so that the user can input a task and add it to the list:

        val inputTextField =  findViewById<EditText>(R.id.addTaskField)

        // Get a reference to the button
        // Set an OnClickListener to it
        findViewById<Button>(R.id.button).setOnClickListener {

            // 1. Grab the text user has inputted into @id/addTaskField
            val userInputtedTask = inputTextField.text.toString()

            // 2. Add the string to our list of tasks: listOfTasks
            listOfTasks.add(userInputtedTask)

            // Notify the data adapter that data has been updated
            adapter.notifyItemInserted(listOfTasks.size - 1)

            // 3. Reset text field
            inputTextField.setText("")

        }


    }
}