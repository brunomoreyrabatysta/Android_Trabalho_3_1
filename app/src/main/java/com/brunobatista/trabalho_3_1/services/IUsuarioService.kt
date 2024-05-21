package com.brunobatista.trabalho_3_1.services

import com.brunobatista.trabalho_3_1.model.Result
import com.brunobatista.trabalho_3_1.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface IUsuarioService {

    @POST("v1/accounts/login")
    fun Login(@Query("teste") teste: String): Call<String>


    //    @GET("v1/6a85a3df-8d50-4d42-a01d-d1bde186e4a6")
    @GET("v1/usuarios")
    fun getAll(): Call<User>

    @GET("v1/usuarios/{usuarioId}")
    fun getId(@Path("usuarioId") usuarioId: Int): Call<Result>
}