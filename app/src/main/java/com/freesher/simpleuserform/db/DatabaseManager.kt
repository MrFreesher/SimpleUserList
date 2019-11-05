package com.freesher.simpleuserform.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.freesher.simpleuserform.model.User


class DatabaseManager(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        private val DB_NAME = "users"
        private val DB_VERSION = 1
    }

    private lateinit var db: SQLiteDatabase
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS  users")
        onCreate(db)
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query =
            "CREATE TABLE users(id INTEGER  PRIMARY KEY AUTOINCREMENT,firstName TEXT,lastName TEXT)"
        db?.execSQL(query)
    }

    fun insertData(user: User) {
        db = writableDatabase
        val values = ContentValues().apply {
            put("firstName", user.firstName)
            put("lastName", user.lastName)
        }
        val newRow = db?.insert("users", null, values)
    }

    fun getAllUsers(): MutableList<User> {
        val columns = listOf("id", "firstName", "lastName").toTypedArray()
        db = readableDatabase
        val cursor = db.query("users", columns, null, null, null, null, null)
        val users = mutableListOf<User>()
        with(cursor) {
            while (moveToNext()) {
                val itemId = getInt(getColumnIndexOrThrow("id"))
                val firstName = getString(getColumnIndexOrThrow("firstName"))
                val lastName = getString(getColumnIndexOrThrow("lastName"))
                users.add(User(itemId, firstName, lastName))

            }
        }
        cursor.close()
        return users
    }

    fun getUserById(id: Int): User {
        val columns = listOf("id", "firstName", "lastName").toTypedArray()
        db = readableDatabase
        val user = User()
        val args = listOf<String>("$id").toTypedArray()
        val cursor = db.query(DB_NAME, columns, "id=?", args, null, null, null)
        if (cursor != null) {
            cursor.moveToFirst()
            user.id = cursor.getInt(0)
            user.firstName = cursor.getString(1)
            user.lastName = cursor.getString(2)

        }
        cursor.close()
        return user

    }

    fun updateUser(user: User): User {
        db = writableDatabase
        val args = listOf<String>(user.id.toString()).toTypedArray()
        val values = ContentValues().apply {
            put("firstName", user.firstName)
            put("lastName", user.lastName)
        }
        db.update(DB_NAME, values, "id=?", args)
        val newUser = getUserById(user.id)
        return newUser
    }
}