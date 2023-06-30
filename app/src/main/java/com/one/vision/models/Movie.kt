package com.one.vision.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Movie(
    val id: String?,
    val image: String?,
    val title: String?,
    val rating: String?,
    val year: String?,
    val duration : String?,
    val tags: ArrayList<String>?,
    val description: String?,
    val languages : ArrayList<String>?,
    val casts : ArrayList<Cast>?,
    val seasonsList: ArrayList<Season>?,
    val ottPlatform: String?,
    val isPrime: Boolean?
): Parcelable