package com.brunobatista.trabalho_3_1.services

import com.brunobatista.trabalho_3_1.model.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IVeiculoService {

    @GET("v1/veiculos")
    fun getAll(): Call<Result>

    @GET("v1/usuarios")
    fun getById(@Query("veiculoId") veiculoId: Int): Call<Result>
}