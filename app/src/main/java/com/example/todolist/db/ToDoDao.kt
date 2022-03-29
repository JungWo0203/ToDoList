package com.example.todolist.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ToDoDao {
    @Query("SELECT * FROM TodoEntity")
    fun getAll() : List<TodoEntity>

    @Insert
    fun insertTodo(todo: TodoEntity)

    @Delete
    fun deleteTodo(todo : TodoEntity)
}