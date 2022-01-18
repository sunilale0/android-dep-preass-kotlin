package com.example.simpletodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    val listOfTasks = mutableListOf<String>()
    lateinit var adapter:TaskItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val onLongClickListener = object : TaskItemAdapter.OnLongClickListener{
            override fun onItemLongClicked(position: Int) {
                // 1. Remove the item from the list
                listOfTasks.removeAt(position)
                // 2. Notify the adapter that our data set has changed
                adapter.notifyDataSetChanged()
            }

        }

        // 1. Let's detect when the user clicks on the add button
        findViewById<Button>(R.id.button).setOnClickListener{
            // Code in here is going to be execute d when the user clicks on a button
            Log.i("caren", "clicked on... ")
        }

        listOfTasks.add("Do laundry")
        listOfTasks.add("Go for a walk")

        // Lookup the recyclerview in activity layout
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView) as RecyclerView

        // Create adapter passing in the sample user data
        adapter = TaskItemAdapter(listOfTasks, onLongClickListener)

        // Attach the adapter to the recyclerview to populate items
        recyclerView.adapter = adapter

        // Set layout manager to position the items
        recyclerView.layoutManager = LinearLayoutManager(this)


        val inputTextField = findViewById<EditText>(R.id.addTaskField)

        // Set up the button and input field, so that the user can end a task and add it to the list
        findViewById<Button>(R.id.button).setOnClickListener{
            // 1. Grab the text the user had inputted into @id/addTaskField
            val userInputtedTask = inputTextField.text.toString();

            // 2. Add the string ot our list of tasks: listOfTasks
            listOfTasks.add(userInputtedTask)

            // notify the adapter that has been updated
            adapter.notifyItemInserted(listOfTasks.size - 1)

            // 3. Reset text field
            inputTextField.setText("")
        }
    }
}