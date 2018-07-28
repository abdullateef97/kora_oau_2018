package com.thanos.kontribute.data.remote.response

data class InitializeTransactionResp(
        var status: Boolean,
        var data: AccessCode,
        var message: String
)

data class AccessCode(
        var access_code: String,
        var reference: String
)