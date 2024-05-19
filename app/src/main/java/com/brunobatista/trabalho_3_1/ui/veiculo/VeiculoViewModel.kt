package com.brunobatista.trabalho_3_1.ui.veiculo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brunobatista.trabalho_3_1.model.Result
import com.brunobatista.trabalho_3_1.model.Veiculo
import com.brunobatista.trabalho_3_1.services.IVeiculoService
import com.brunobatista.trabalho_3_1.services.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VeiculoViewModel : ViewModel(){
    private var listVeiculoLiveData = MutableLiveData<List<Veiculo>>()
    private var veiculoLiveData = MutableLiveData<Veiculo>()
    fun getVeiculoAll() {
        RetrofitClient.createService(IVeiculoService::class.java).getAll()
            .enqueue(object : Callback<Result> {
                override fun onResponse(call: Call<Result>, response: Response<Result>) {
                    if(response.isSuccessful) {
                        listVeiculoLiveData.value = response.body()!!.data as List<Veiculo>
                    }
                }

                override fun onFailure(call: Call<Result>, t: Throwable) {
                    Log.d("VeiculoViewModel", t.message.toString())
                }
            })
    }

    fun observeListVeiculoLiveData(): LiveData<List<Veiculo>> {
        return listVeiculoLiveData
    }

    fun getVeiculoById(veiculoId: Int) {
        RetrofitClient.createService(IVeiculoService::class.java).getById(veiculoId)
            .enqueue(object : Callback<Result> {
                override fun onResponse(call: Call<Result>, response: Response<Result>) {
                    if(response.isSuccessful) {
                        veiculoLiveData.value = response.body()!!.data as Veiculo
                    }
                }

                override fun onFailure(call: Call<Result>, t: Throwable) {
                    Log.d("VeiculoViewModel", t.message.toString())
                }
            })
    }

    fun observeVeiculoLiveData(): LiveData<Veiculo> {
        return veiculoLiveData
    }
}