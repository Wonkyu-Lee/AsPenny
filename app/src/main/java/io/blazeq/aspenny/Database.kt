package io.blazeq.aspenny

import com.google.firebase.database.FirebaseDatabase
import io.blazeq.aspenny.models.Source

object Database {
    private const val userKey = "-LZSyN_wqEojcm048lN0"
    private val root = FirebaseDatabase.getInstance().reference
    private val users = root.child("users")
    private val user = users.child(userKey)
    private val sources = user.child("sources")

    fun addItem(source: Source) {
        val key = sources.push().key
        if (key != null) {
            sources.child(key).setValue(source)
        }
    }
}