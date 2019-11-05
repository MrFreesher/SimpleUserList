package com.freesher.simpleuserform

import UserListAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.freesher.simpleuserform.adapter.UserAdapter
import com.freesher.simpleuserform.model.User
import kotlinx.android.synthetic.main.activity_main.*
import android.app.Activity
import android.content.Context

import android.view.inputmethod.InputMethodManager
import com.freesher.simpleuserform.db.DatabaseManager


class MainActivity : AppCompatActivity() {

    private var listOfUsers = mutableListOf<User>()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var userAdapter: UserAdapter
    private lateinit var database: DatabaseManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addUser_btn.setOnClickListener { addUser() }



        linearLayoutManager =
            LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        database = DatabaseManager(this@MainActivity)
        listOfUsers = database.getAllUsers()
        //For recyclerview
        userAdapter = UserAdapter(listOfUsers, this@MainActivity)
        user_recyclerView.layoutManager = linearLayoutManager
        user_recyclerView.adapter = userAdapter
//For listview
//        val userAdapter = UserListAdapter(this@MainActivity, listOfUsers)
//        user_listview.adapter = userAdapter
    }

    override fun onResume() {
        super.onResume()
        listOfUsers.clear()
        listOfUsers.addAll(database.getAllUsers())
        userAdapter.notifyDataSetChanged()
    }


    fun addUser() {
        val firstName = firstName_tv.text.toString()
        val lastName = lastName_tv.text.toString()

        val user = User(listOfUsers.size, firstName, lastName)
        database.insertData(user)
        activeUser_tv.text = user.toString()

        listOfUsers.clear()
        listOfUsers.addAll(database.getAllUsers())
        userAdapter.notifyDataSetChanged()
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
//Hide:
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)


    }
}
