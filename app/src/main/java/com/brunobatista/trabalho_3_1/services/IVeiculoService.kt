package com.brunobatista.trabalho_3_1.services

import com.brunobatista.trabalho_3_1.model.ResultVeiculo
import com.brunobatista.trabalho_3_1.model.ResultVeiculoList
import com.brunobatista.trabalho_3_1.model.Veiculo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface IVeiculoService {

    @GET("v1/veiculos")
    fun getAll(): Call<ResultVeiculoList>

    @GET("v1/veiculos/{veiculoId}")
    fun getById(@Path("veiculoId") veiculoId : Int): Call<ResultVeiculo>

    @DELETE("v1/veiculos/{veiculoId}")
    fun DeleteVeiculo(@Path("veiculoId") veiculoId: Int): Call<ResultVeiculo>

    @Headers("Content-Type: application/json")
    @POST("v1/veiculos")
    fun AddVeiculo(@Body veiculo: Veiculo): Call<ResultVeiculo>

    @Headers("Content-Type: application/json")
    @PUT("v1/veiculos/{veiculoId}")
    fun UpdateVeiculo(@Path("veiculoId") veiculoId: Int, @Body veiculo: Veiculo): Call<ResultVeiculo>
}