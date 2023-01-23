package com.example.musicwiki.model

data class ArtistInfo(
    var artist: ArtistDetail? = null
)
data class ArtistDetail (
    var stats: Stats? = null
        )

data class Stats (
    var listeners: String? = null, var playcount: String? = null
        )
