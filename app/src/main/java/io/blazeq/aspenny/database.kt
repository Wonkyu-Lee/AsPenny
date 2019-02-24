package io.blazeq.aspenny

import com.google.firebase.database.FirebaseDatabase
import io.blazeq.aspenny.model.Source
import io.blazeq.aspenny.model.User

object Database {
    private val root = FirebaseDatabase.getInstance().reference
    private val users = root.child("users")
    private const val userKey = "-LZSyN_wqEojcm048lN0"
    val user = users.child(userKey)
    val sources = user.child("sources")

    fun addUser() {
        val key = users.push().key
        if (key != null) {
            users.child(key).setValue(User(key))
        }
    }

    fun addItem(source: Source) {
        val key = sources.push().key
        if (key != null) {
            sources.child(key).setValue(source)
        }
    }
}