package com.frobengineering.getposts.data

data class Post(
    val userId: Long,
    val id: Long,
    val title: String = "Title",
    val body: String = "Body"
)
