package com.example.musicwiki.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageSizes(
    @Json(name = "#text") var text: String? = null, @Json(name = "size") var size: String? = null
): Parcelable
