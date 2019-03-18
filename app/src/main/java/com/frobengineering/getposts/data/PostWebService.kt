package com.frobengineering.getposts.data

import kotlinx.coroutines.Deferred
import retrofit2.http.GET

/** Created by Froejo, 2019-03-17.
 *
 *
 */

interface PostWebService {

    @GET("/posts")
    fun getPostsAsync(): Deferred<List<Post>>

}