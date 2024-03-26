package com.example.todo_app

sealed class TaskCategory(var isSelected:Boolean = true) {
    object personal:TaskCategory()
    object negocios:TaskCategory()
    object estudios:TaskCategory()
    object otros:TaskCategory()
}