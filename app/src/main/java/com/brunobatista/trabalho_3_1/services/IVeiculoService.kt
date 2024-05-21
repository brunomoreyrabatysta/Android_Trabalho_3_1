package com.brunobatista.trabalho_3_1.services

import com.brunobatista.trabalho_3_1.model.Result
import com.brunobatista.trabalho_3_1.model.Vehicle
import com.brunobatista.trabalho_3_1.model.Vehicles
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IVeiculoService {

//
//    @GET("v1/ef6b8130-c09d-4ecd-91d4-007396e03aa5")
    @GET("v1/veiculos")
    fun getAll(): Call<Vehicle>

    @GET("v1/veiculos/{veiculoId}")
    fun getById(@Path("veiculoId") veiculoId : Int): Call<Result>
}