package com.thanos.kontribute.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Group (
        var id: String = "",
        var title: String,
        var description: String,
        var color: Int
) : Parcelable