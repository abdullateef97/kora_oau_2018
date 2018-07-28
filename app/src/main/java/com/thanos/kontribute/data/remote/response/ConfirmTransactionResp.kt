package com.thanos.kontribute.data.remote.response

data class ConfirmTransactionResp(
        var status: Boolean,
        var data: TransactionResp,
        var message: String
)

data class TransactionResp(
        var auth_code:String,
        var amount: String,
        var paid_at: String,
        var last4: String,
        var bank: String,
        var created_at: String,
        var transaction_date: String
)
