package com.example.musicwiki.eventBus

data class GenreEvent(
    var genre: String, var stat: String
): MessageEvent(stat)
