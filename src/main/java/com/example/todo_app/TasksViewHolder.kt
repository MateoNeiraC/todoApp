package com.example.todo_app

import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.RecyclerView

class TasksViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val cbTask: CheckBox = view.findViewById(R.id.cbTask)
    private val tvTask:TextView= view.findViewById(R.id.tvTask)

    fun render(task: Task){
        tvTask.text = task.name
        if (task.isSelected) {
            tvTask.paintFlags = tvTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            tvTask.paintFlags = tvTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
        cbTask.isChecked = task.isSelected
    }
}