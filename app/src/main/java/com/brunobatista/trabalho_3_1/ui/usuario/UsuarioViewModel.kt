package com.brunobatista.trabalho_3_1.ui.usuario

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brunobatista.trabalho_3_1.model.Result
import com.brunobatista.trabalho_3_1.model.Usuario
import com.brunobatista.trabalho_3_1.services.IUsuarioService
import com.brunobatista.trabalho_3_1.services.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsuarioViewModel : ViewModel() {
    private var listUsuarioLiveData = MutableLiveData<List<Usuario>>()
    private var usuarioLiveData = MutableLiveData<Usuario>()

    fun getUsuarioAll() {
        RetrofitClient.createService(IUsuarioService::class.java).getAll()
            .enqueue(object : Callback<Result> {
                override fun onResponse(call: Call<Result>, response: Response<Result>) {
                    if (response.isSuccessful) {
                        listUsuarioLiveData.value = response.body()!!.data as List<Usuario>
                    }
                }

                override fun onFailure(call: Call<Result>, t: Throwable) {
                    Log.d("UsuarioViewModel", t.message.toString())
                }
            })
    }

    fun observeListUsuarioLiveData(): LiveData<List<Usuario>> {
        return listUsuarioLiveData
    }

    fun getUsuarioById(usuarioId: Int) {
        RetrofitClient.createService(IUsuarioService::class.java).getId(usuarioId)
            .enqueue(object : Callback<Result> {
                override fun onResponse(call: Call<Result>, response: Response<Result>) {
                    if(response.isSuccessful) {
                        usuarioLiveData.value = response.body()!!.data as Usuario
                    }
                }

                override fun onFailure(call: Call<Result>, t: Throwable) {
                    Log.d("UsuarioViewModel", t.message.toString())
                }

            })
    }

    fun observeUsuarioLiveData(): LiveData<Usuario> {
        return usuarioLiveData
    }
}