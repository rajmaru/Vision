package com.one.vision.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Episode(
    val number: String?,
    val title: String?,
    val image: String?,
    val date: String?,
    val duration: String?
    ) : Parcelable