package com.freesher.simpleuserform.model

data class User(var id: Int = 0, var firstName: String = "", var lastName: String = "") {
    override fun toString(): String {
        return "${id} - ${firstName} - ${lastName}"
    }


}
