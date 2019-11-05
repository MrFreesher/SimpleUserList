import android.R
import android.content.Context
import android.widget.TextView
import android.graphics.Movie
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.annotation.LayoutRes
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.freesher.simpleuserform.model.User
import kotlinx.android.synthetic.main.list_item.view.*


class UserListAdapter(private val mContext: Context, list: MutableList<User>) :
    ArrayAdapter<User>(mContext, 0, list) {
    private var userList = mutableListOf<User>()

    init {
        userList = list
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItem: View? = convertView
        if (listItem == null)
            listItem = LayoutInflater.from(context).inflate(
                com.freesher.simpleuserform.R.layout.list_item,
                parent,
                false
            )

        val currentUser = userList.get(position)



        listItem!!.firstNameContent.text = currentUser.firstName
        listItem!!.lastNameContent.text = currentUser.lastName
        listItem!!.id_content.text = currentUser.id.toString()

        return listItem
    }
}