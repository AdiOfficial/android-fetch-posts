package com.frobengineering.getposts.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.frobengineering.getposts.data.Post
import com.frobengineering.getposts.data.PostRepository

/** Created by Froejo, 2019-03-17.
 *
 *
 */

class PostListViewModelFactory(private val postRepository: PostRepository) : ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostListViewModel::class.java)) {

            @Suppress("UNCHECKED_CAST")
            return PostListViewModel(postRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}


class PostListViewModel(private val postRepository: PostRepository) : ViewModel() {

    fun getPosts(numberOfPosts: Int): LiveData<List<Post>> {

        return Transformations.map(postRepository.getPosts()) {
            it.subList(0, numberOfPosts)
        }
    }

}