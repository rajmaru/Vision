package com.one.vision.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tag(
    val name: String?,
    val image: Int?
    ) : Parcelable