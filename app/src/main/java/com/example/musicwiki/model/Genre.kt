package com.example.musicwiki.model

data class GenreModel(
 val toptags: Genre? = null
)

data class Genre(
    val tag: List<Genres>? = null
)
data class Genres(
    val name: String? = null, val count: Int? = null, val reach: Int? = null
)