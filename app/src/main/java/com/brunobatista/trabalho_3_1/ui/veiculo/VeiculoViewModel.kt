package com.brunobatista.trabalho_3_1.ui.veiculo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brunobatista.trabalho_3_1.model.Result
import com.brunobatista.trabalho_3_1.model.ResultVeiculo
import com.brunobatista.trabalho_3_1.model.ResultVeiculoList
import com.brunobatista.trabalho_3_1.model.Veiculo
import com.brunobatista.trabalho_3_1.services.IVeiculoService
import com.brunobatista.trabalho_3_1.services.RetrofitClient
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class VeiculoViewModel : ViewModel(){
    private var listVeiculoLiveData = MutableLiveData<List<Veiculo>>()
    private var veiculoLiveData = MutableLiveData<Veiculo>()
    fun getVeiculoAll() {
        RetrofitClient.createService(IVeiculoService::class.java).getAll()
            .enqueue(object : Callback<ResultVeiculoList> {
                override fun onResponse(call: Call<ResultVeiculoList>, response: Response<ResultVeiculoList>
                ) {
                    if(response.isSuccessful) {
                        listVeiculoLiveData.value = response.body()!!.data
                    } else {
                        listVeiculoLiveData.value = null
                    }
                }

                override fun onFailure(call: Call<ResultVeiculoList>, t: Throwable) {
                    Log.d("VeiculoViewModel", t.message.toString())
                }
            })
    }

    fun observeListVeiculoLiveData(): LiveData<List<Veiculo>> {
        return listVeiculoLiveData
    }

    fun getVeiculoById(veiculoId: Int) {
        RetrofitClient.createService(IVeiculoService::class.java).getById(veiculoId)
            .enqueue(object : Callback<ResultVeiculo> {
                override fun onResponse(call: Call<ResultVeiculo>, response: Response<ResultVeiculo>
                ) {
                    if(response.isSuccessful) {
                        veiculoLiveData.value = response.body()!!.data
                    } else {
                        Log.d("VeiculoViewModel",response.message())
                    }
                }

                override fun onFailure(call: Call<ResultVeiculo>, t: Throwable) {
                    Log.d("VeiculoViewModel", t.message.toString())
                }
            })
    }

    fun observeVeiculoLiveData(): LiveData<Veiculo> {
        return veiculoLiveData
    }
}