package com.one.vision.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Movie(
    val id: String?,
    val title: String?,
    val description: String?,
    val poster: String?,
    val rating: String?,
    val ottPlatform: String?,
    val isPrime: Boolean?
): Parcelable