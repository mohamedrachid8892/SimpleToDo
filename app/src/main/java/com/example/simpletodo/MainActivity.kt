package com.example.simpletodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException
import java.nio.charset.Charset
import java.nio.file.Files

class MainActivity : AppCompatActivity() {

    var listOfTasks = mutableListOf<String>()
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

                saveItems()
            }
        }

        loadItems()

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

            saveItems()

        }
    }

    // Save the data the user has inputted
    // Save data by writing and reading from the file

    // Get the data file we need
    fun getDataFile() : File {

        // Every line represents a specific task in our list of tasks
        return File(filesDir, "data.txt")
    }

    // Load the items by reading every line in the file
    fun loadItems() {
        try {
            listOfTasks = FileUtils.readLines(getDataFile(), Charset.defaultCharset())
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
    }

    // Save items by writing each element in the list to a line in the file
    fun saveItems() {
        try {
            FileUtils.writeLines(getDataFile(), listOfTasks)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
    }
}