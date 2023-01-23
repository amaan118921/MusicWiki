package com.example.musicwiki.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TopAlbums (
    var topalbums: AlbumModel? = null
        ): Parcelable