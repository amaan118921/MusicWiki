package com.example.musicwiki.model

data class GenreInfo(
    var tag: GenreDesc? = null
)

data class GenreDesc(
    var name: String? = null, var wiki: Information? = null
)
data class Information(
    var summary: String? = null
)
