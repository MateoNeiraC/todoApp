package com.example.todo_app

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class CategoriesViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val tvCategory: TextView = view.findViewById(R.id.tvCategoryName)
    private val divider1: View = view.findViewById(R.id.divider1)
    private val divider2: View = view.findViewById(R.id.divider2)
    private val viewContainer: CardView = view.findViewById(R.id.viewContainer)

    fun render(taskCategory: TaskCategory, onItemSelected: (Int) -> Unit) {
        tvCategory.text = "Ejemplo"

        itemView.setOnClickListener{onItemSelected(layoutPosition)}

        val color = if (taskCategory.isSelected) {
            R.color.todo_background_card
        } else {
            R.color.todo_background_disabled
        }

        viewContainer.setCardBackgroundColor(ContextCompat.getColor(viewContainer.context, color))


        when(taskCategory){
            TaskCategory.estudios -> {
                tvCategory.text = "Estudios"
                divider1.setBackgroundColor(ContextCompat.getColor(divider1.context, R.color.todo_estudios_category))
                divider2.setBackgroundColor(ContextCompat.getColor(divider2.context, R.color.todo_estudios_category))
            }
            TaskCategory.negocios -> {
                tvCategory.text = "Negocios"
                divider1.setBackgroundColor(ContextCompat.getColor(divider1.context, R.color.todo_business_category))
                divider2.setBackgroundColor(ContextCompat.getColor(divider2.context, R.color.todo_business_category))
            }
            TaskCategory.otros -> {
                tvCategory.text = "Otros"
                divider1.setBackgroundColor(ContextCompat.getColor(divider1.context, R.color.todo_other_category))
                divider2.setBackgroundColor(ContextCompat.getColor(divider2.context, R.color.todo_other_category))
            }
            TaskCategory.personal -> {
                tvCategory.text = "Personal"
                divider1.setBackgroundColor(ContextCompat.getColor(divider1.context, R.color.todo_personal_category))
                divider2.setBackgroundColor(ContextCompat.getColor(divider2.context, R.color.todo_personal_category))
            }
        }
    }
}