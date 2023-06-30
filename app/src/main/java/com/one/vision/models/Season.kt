package com.one.vision.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Season (
    val name: String?,
    val episodesList: ArrayList<Episode>?
): Parcelable