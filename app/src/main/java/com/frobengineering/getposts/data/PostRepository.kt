package com.frobengineering.getposts.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val SERVER_BASE_URL = "https://jsonplaceholder.typicode.com/"

class PostRepository {

    private val retrofitClient = Retrofit.Builder()
        .baseUrl(SERVER_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    private val webService = retrofitClient.create(PostWebService::class.java)

    private val userCache = MutableLiveData<List<Post>>()

    fun getPosts(): LiveData<List<Post>> {
        if (userCache.value == null)
            CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main) { userCache.postValue(getNewPostsFromServer()) }
            }

        return userCache
    }

    private suspend fun getNewPostsFromServer(): List<Post> {
        return webService.getPostsAsync().await()
    }

}
