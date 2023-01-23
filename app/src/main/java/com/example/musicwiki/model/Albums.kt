package com.example.musicwiki.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Albums(
    var albums: AlbumModel? = null
): Parcelable
@Parcelize
data class AlbumModel (
    var album: List<Album>? = null
        ): Parcelable
@Parcelize
data class Album(
    var name: String? = null, var artist: Artist? = null, var image: List<ImageSizes>? = null,
): Parcelable
@Parcelize
data class Artist (
    var name: String? = null, var mbid: String? = null, var url: String? = null, var image: List<ImageSizes>? = null
        ): Parcelable

