package com.freesher.simpleuserform.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.freesher.simpleuserform.DetailsActivity
import com.freesher.simpleuserform.R
import com.freesher.simpleuserform.model.User
import kotlinx.android.synthetic.main.list_item.view.*

class UserAdapter(val usersList: MutableList<User>, val context: Context) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val viewHolder = UserViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.list_item,
                parent,
                false
            )
        )
        return viewHolder
    }

    override fun getItemCount(): Int = usersList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder?.firstNameContent.text = usersList[position].firstName
        holder?.lastNameContent.text = usersList[position].lastName
        holder?.idContent.text = usersList[position].id.toString()

    }

    class UserViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val firstNameContent: TextView
        val lastNameContent: TextView
        val idContent: TextView


        init {
            idContent = view.id_content
            firstNameContent = view.firstNameContent
            lastNameContent = view.lastNameContent
            view.setOnClickListener {
                val intent: Intent = Intent(view.context, DetailsActivity::class.java)
                intent.putExtra("firstName", firstNameContent.text)
                intent.putExtra("lastName", lastNameContent.text)
                intent.putExtra("id", idContent.text)
                view.context.startActivity(intent)
            }
        }
    }
}