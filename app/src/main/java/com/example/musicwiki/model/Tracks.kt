package com.example.musicwiki.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
data class Tracks(
    var tracks: TrackModel? = null
): Parcelable
@Parcelize
data class TrackModel(
    var track: List<Track>? = null
): Parcelable
@Parcelize
data class Track(
    var name: String? = null, var duration: String? = null, var mbid: String? = null, var artist: Artist? = null,
    var image: List<ImageSizes>? = null
): Parcelable