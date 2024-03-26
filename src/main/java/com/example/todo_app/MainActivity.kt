package com.example.todo_app

import android.app.Dialog
import android.os.Bundle
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val categories = listOf(
        TaskCategory.estudios,
        TaskCategory.negocios,
        TaskCategory.personal,
        TaskCategory.otros
    )

    val tasks = mutableListOf(Task("Ejemplo", TaskCategory.personal))

    private lateinit var rvCategories:RecyclerView
    private lateinit var CategoriesAdapter:CategoriesAdapter

    private lateinit var rvTasks:RecyclerView
    private lateinit var TasksAdapter:TasksAdapter

    private lateinit var fabAddTask: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        varInitializer()
        uiInitializer()
        listenerInitializer()
    }

    private fun varInitializer() {
        rvCategories = findViewById(R.id.rvCategories)
        rvTasks = findViewById(R.id.rvTasks)
        fabAddTask = findViewById(R.id.fabAddTask)
    }

    private fun uiInitializer() {
        CategoriesAdapter = CategoriesAdapter(categories) {position -> updateCategories(position)}
        rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = CategoriesAdapter

        TasksAdapter = TasksAdapter(tasks, {position -> onItemSelected(position)})
        rvTasks.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvTasks.adapter = TasksAdapter
    }

    private fun listenerInitializer(){
        fabAddTask.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_task)
        dialog.show()

        val etNewTask:EditText = dialog.findViewById(R.id.etNewTask)
        val btnNewTask:Button = dialog.findViewById(R.id.btnNewTask)
        val rgCategories:RadioGroup = dialog.findViewById(R.id.rgCategories)

        btnNewTask.setOnClickListener {
            val selectedId = rgCategories.checkedRadioButtonId
            val selectedRadioButton: RadioButton = rgCategories.findViewById(selectedId)
            val current:TaskCategory = when (selectedRadioButton.text){
                "Negocios" -> TaskCategory.negocios
                "Personal" -> TaskCategory.personal
                "Estudios" -> TaskCategory.estudios
                else -> {TaskCategory.otros}
            }

            tasks.add(Task(etNewTask.text.toString(), current))
            updateAdapter()
            dialog.hide()
        }
    }

    private fun onItemSelected(position: Int){
        tasks[position].isSelected = !tasks[position].isSelected
        updateAdapter()
    }

    private fun updateAdapter(){
        val selectedCategories: List<TaskCategory> = categories.filter { it.isSelected }
        val newTasks = tasks.filter { selectedCategories.contains(it.category) }
        TasksAdapter.tasks = newTasks
        TasksAdapter.notifyDataSetChanged()
    }

    private fun updateCategories(position: Int){
        categories[position].isSelected = !categories[position].isSelected
        CategoriesAdapter.notifyItemChanged(position)
        updateAdapter()
    }
}