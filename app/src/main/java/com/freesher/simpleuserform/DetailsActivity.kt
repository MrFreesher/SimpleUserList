package com.freesher.simpleuserform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val extras = intent.extras
        if (extras != null) {
            firstNameContent.text = extras.getString("firstName")
            lastNameContent.text = extras.getString("lastName")
        }
    }
}
