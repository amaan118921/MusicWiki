package com.example.musicwiki.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TopTracks (
    var toptracks: TrackModel? = null
        ): Parcelable