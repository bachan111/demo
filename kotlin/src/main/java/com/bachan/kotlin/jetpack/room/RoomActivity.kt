package com.bachan.kotlin.jetpack.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bachan.kotlin.R
import kotlin.concurrent.thread

class RoomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        val userDao = AppDatabase.getDatabase(this).userDao()
        val user1 = User("Tom", "Joy", 20)

        val user2 = User("Tom2", "Joy2", 20)
        user1.id = userDao.insertUser(user1)
        user2.id = userDao.insertUser(user2)

        userDao.deleteUserByLastName("Joy")

        thread {
            for (user in userDao.loadAllUsers()) {
                println(user.toString())
            }
        }
    }
}