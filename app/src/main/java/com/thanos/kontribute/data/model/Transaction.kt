package com.thanos.kontribute.data.model

data class Transaction (
        var id: String = "",
        var amount: Int,
        var group: String,
        var date: String,
        var deposit: Boolean,
        var withdrawal: Boolean
)