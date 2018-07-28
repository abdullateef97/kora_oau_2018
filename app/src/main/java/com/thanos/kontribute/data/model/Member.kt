package com.thanos.kontribute.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Member (
        var id: String = "",
        var name: String,
        var pictureUrl: String,
        var isAdmin: Boolean
) : Parcelable