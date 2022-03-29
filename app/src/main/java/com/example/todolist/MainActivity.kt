package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Dao
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.db.AppDatabase
import com.example.todolist.db.ToDoDao
import com.example.todolist.db.TodoEntity

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private lateinit var db : AppDatabase
    private lateinit var todoDao: ToDoDao
    private lateinit var todoList : ArrayList<TodoEntity>
    private lateinit var adapter : ToDoRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener{
            val intent = Intent(this, AddTodoActivity::class.java)
            startActivity(intent)
        }

        // DB에 인스턴스를 가져오고 DB 작업을 할 수 있는 DAO 를 가져온다.
        db = AppDatabase.getInstance(this)!!
        todoDao = db.getToDoDao()

        getAllTodoList()
    }

    private fun getAllTodoList() {
        Thread{
            todoList = ArrayList(todoDao.getAll())
            setRecyclerView()
        }
    }

    private fun setRecyclerView(){
        runOnUiThread {
            adapter = ToDoRecyclerViewAdapter(todoList) //어뎁터 객체 할당
            binding.recyclerView.adapter = adapter
            //리사이클러뷰가 어댑터로 위에서 만든 어댑터 설정
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            //레이아웃 메니저 설정
        }
    }

    override fun onRestart() {
        super.onRestart()
        getAllTodoList()
    }
}