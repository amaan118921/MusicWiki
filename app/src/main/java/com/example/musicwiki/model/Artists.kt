package com.example.musicwiki.model

data class Artists(
    var topartists: ArtistModel? = null
)

data class ArtistModel (
    var artist: List<Artist>? = null
        )