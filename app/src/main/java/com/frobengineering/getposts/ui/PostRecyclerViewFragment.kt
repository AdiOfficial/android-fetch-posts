package com.frobengineering.getposts.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.frobengineering.getposts.R
import com.frobengineering.getposts.data.Post
import com.frobengineering.getposts.data.PostRepository
import kotlinx.android.synthetic.main.fragment_post_list.*


const val NUMBER_OF_POSTS = 50

class PostRecyclerViewFragment : Fragment() {


    private var listener: OnListFragmentInteractionListener? = null

    private val viewModel by lazy {
        val repository = PostRepository()
        val postListViewModelFactory = PostListViewModelFactory(repository)
        ViewModelProviders.of(this.activity!!, postListViewModelFactory).get(PostListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_post_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                adapter = PostRecyclerViewAdapter()
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPosts(NUMBER_OF_POSTS)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun getPosts(numberOfPosts: Int) {
        viewModel.getPosts(numberOfPosts).observe(this, Observer {

            with((list.adapter as PostRecyclerViewAdapter)) {
                mValues = it
                Log.d("RecyclerViewFragment", "Size of dataset: ${it.size}")
                notifyDataSetChanged()
            }
        })
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: Post?)
    }

    companion object {
        @JvmStatic
        fun newInstance() = PostRecyclerViewFragment()
    }
}
