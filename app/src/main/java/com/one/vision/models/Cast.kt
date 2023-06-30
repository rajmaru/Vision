package com.one.vision.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cast(
    val name: String?,
    val image: String?
) : Parcelable