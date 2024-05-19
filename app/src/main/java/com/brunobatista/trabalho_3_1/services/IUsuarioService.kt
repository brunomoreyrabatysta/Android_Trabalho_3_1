package com.brunobatista.trabalho_3_1.services

import com.brunobatista.trabalho_3_1.model.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IUsuarioService {

    @POST("v1/accounts/login")
    fun Login(@Query("teste") teste: String): Call<String>


    @GET("v1/usuarios")
    fun getAll(): Call<Result>

    @GET("v1/usuarios")
    fun getId(@Query("usuarioId") usuarioId: Int): Call<Result>
}