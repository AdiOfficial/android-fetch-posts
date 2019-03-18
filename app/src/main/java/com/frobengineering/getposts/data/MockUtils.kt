package com.frobengineering.getposts.data

/** Created by Froejo, 2019-03-17.
 *
 *
 */

var mockPostId = 1L
var mockPostUserId = 1L

fun getMockPosts(): List<Post>{

    return listOf(getMockPost(), getMockPost(), getMockPost(), getMockPost())

}

fun getMockPost(): Post {
    return Post(mockPostId++, mockPostUserId++)
}
