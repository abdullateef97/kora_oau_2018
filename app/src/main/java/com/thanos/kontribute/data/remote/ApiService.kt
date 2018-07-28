package com.atlascc.kontribute.data.remote

import com.thanos.kontribute.data.remote.response.ConfirmTransactionResp
import com.thanos.kontribute.data.remote.response.InitializeTransactionResp
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("get_access_code")
    @FormUrlEncoded
    fun initializeTransaction(
            @Field("email") email: String,
            @Field("amount") amount: String
    ): Call<InitializeTransactionResp>

    @GET("verify/{reference}/{save_auth}/{user_id}")
    fun confirmTransaction(
            @Path("reference") reference: String,
            @Path("save_auth") saveAuth: Boolean,
            @Path("user_id") userId: String
    ): Call<ConfirmTransactionResp>
}