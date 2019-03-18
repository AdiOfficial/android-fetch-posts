package com.frobengineering.getposts.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.frobengineering.getposts.R
import com.frobengineering.getposts.data.Post

class MainActivity : AppCompatActivity(), PostRecyclerViewFragment.OnListFragmentInteractionListener {

    override fun onListFragmentInteraction(item: Post?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showFragment()
    }

    private fun showFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, PostRecyclerViewFragment.newInstance())
            .commit()
    }
}
