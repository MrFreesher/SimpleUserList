package com.freesher.simpleuserform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.freesher.simpleuserform.db.DatabaseManager
import com.freesher.simpleuserform.model.User
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    private lateinit var database: DatabaseManager
    private var id: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val extras = intent.extras
        if (extras != null) {
            id = extras.getString("id")!!.toInt()
            firstNameContent.setText(extras.getString("firstName", ""))
            lastNameContent.setText(extras.getString("lastName", ""))
        }
        database = DatabaseManager(this@DetailsActivity)
        editUserBtn.setOnClickListener { editUser() }
    }

    fun editUser() {
        val firstName = firstNameContent.text.toString()
        val lastName = lastNameContent.text.toString()
        val user = User(id, firstName, lastName)
        val updatedUser = database.updateUser(user)
        firstNameContent.setText(updatedUser.firstName)
        lastNameContent.setText(updatedUser.lastName)

    }
}
