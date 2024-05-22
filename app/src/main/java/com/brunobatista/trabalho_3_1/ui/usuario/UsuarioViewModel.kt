package com.brunobatista.trabalho_3_1.ui.usuario

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brunobatista.trabalho_3_1.model.Result
import com.brunobatista.trabalho_3_1.model.ResultUsuario
import com.brunobatista.trabalho_3_1.model.ResultUsuarioList
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
            .enqueue(object : Callback<ResultUsuarioList> {
                override fun onResponse(call: Call<ResultUsuarioList>, response: Response<ResultUsuarioList>
                ) {
                    if (response.isSuccessful) {
                        listUsuarioLiveData.value = response.body()!!.data
                    }
                }

                override fun onFailure(call: Call<ResultUsuarioList>, t: Throwable) {
                    Log.d("UsuarioViewModel", t.message.toString())
                }
            })
    }

    fun observeListUsuarioLiveData(): LiveData<List<Usuario>> {
        return listUsuarioLiveData
    }

    fun getUsuarioById(usuarioId: Int) {
        RetrofitClient.createService(IUsuarioService::class.java).getId(usuarioId)
            .enqueue(object : Callback<ResultUsuario> {
                override fun onResponse(call: Call<ResultUsuario>, response: Response<ResultUsuario>
                ) {
                    if(response.isSuccessful) {
                        usuarioLiveData.value = response.body()!!.data
                    } else {
                        usuarioLiveData.value = null
                    }
                }

                override fun onFailure(call: Call<ResultUsuario>, t: Throwable) {
                    Log.d("UsuarioViewModel", t.message.toString())
                }
            })
    }

    fun observeUsuarioLiveData(): LiveData<Usuario> {
        return usuarioLiveData
    }

    fun AddUsuario(usuario: Usuario) {
        RetrofitClient.createService(IUsuarioService::class.java).AddUsuario(usuario)
            .enqueue(object : Callback<ResultUsuario> {
                override fun onResponse(call: Call<ResultUsuario>, response: Response<ResultUsuario>
                ) {
                    if(response.isSuccessful) {
                        usuarioLiveData.value = response.body()!!.data
                    }
                }

                override fun onFailure(call: Call<ResultUsuario>, t: Throwable) {
                    Log.d("UsuarioViewModel", t.message.toString())
                }
            })
    }

    fun UpdateUsuario(usuarioId: Int, usuario: Usuario) {
        RetrofitClient.createService(IUsuarioService::class.java).UpdateUsuario(usuarioId, usuario)
            .enqueue(object : Callback<ResultUsuario> {
                override fun onResponse(call: Call<ResultUsuario>, response: Response<ResultUsuario>
                ) {
                    if(response.isSuccessful) {
                        usuarioLiveData.value = response.body()!!.data
                    }
                }

                override fun onFailure(call: Call<ResultUsuario>, t: Throwable) {
                    Log.d("UsuarioViewModel", t.message.toString())
                }
            })
    }

    fun DeleteUsuario(usuarioId: Int) {
        RetrofitClient.createService(IUsuarioService::class.java).DeleteUsuario(usuarioId)
            .enqueue(object : Callback<ResultUsuario> {
                override fun onResponse(call: Call<ResultUsuario>, response: Response<ResultUsuario>
                ) {
                    if(response.isSuccessful) {
                        usuarioLiveData.value = response.body()!!.data
                    }
                }

                override fun onFailure(call: Call<ResultUsuario>, t: Throwable) {
                    Log.d("UsuarioViewModel", t.message.toString())
                }
            })
    }
}