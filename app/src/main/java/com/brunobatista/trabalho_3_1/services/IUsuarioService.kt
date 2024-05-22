package com.brunobatista.trabalho_3_1.services

import com.brunobatista.trabalho_3_1.model.Result
import com.brunobatista.trabalho_3_1.model.ResultUsuario
import com.brunobatista.trabalho_3_1.model.ResultUsuarioList
import com.brunobatista.trabalho_3_1.model.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface IUsuarioService {

    @POST("v1/accounts/login")
    fun Login(@Query("teste") teste: String): Call<String>


    @GET("v1/usuarios")
    fun getAll(): Call<ResultUsuarioList>

    @GET("v1/usuarios/{usuarioId}")
    fun getId(@Path("usuarioId") usuarioId: Int): Call<ResultUsuario>

    @DELETE("v1/usuarios/{usuarioId}")
    fun DeleteUsuario(@Path("usuarioId") usuarioId: Int): Call<ResultUsuario>

    @Headers("Content-Type: application/json")
    @POST("v1/usuarios")
    fun AddUsuario(@Body usuario: Usuario): Call<ResultUsuario>

    @Headers("Content-Type: application/json")
    @PUT("v1/usuarios/{usuarioId}")
    fun UpdateUsuario(@Path("usuarioId") usuarioId: Int, @Body usuario: Usuario): Call<ResultUsuario>
}