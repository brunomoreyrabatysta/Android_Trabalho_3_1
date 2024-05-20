package com.brunobatista.trabalho_3_1.ui.veiculo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brunobatista.trabalho_3_1.model.Result
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
        Log.d("VeiculoViewModel" , "teste 7")
        RetrofitClient.createService(IVeiculoService::class.java).getById(veiculoId)
            .enqueue(object : Callback<Result> {
                override fun onResponse(call: Call<Result>, response: Response<Result>) {
                    Log.d("VeiculoViewModel" , "teste 8")

                    if(response.isSuccessful) {
                        Log.d("VeiculoViewModel" , "teste 9")
                        Log.d("VeiculoViewModel" , response.body()!!.data.toString())
                        Log.d("VeiculoViewModel" , Converter(response.body()!!.data.toString()).toString())
                        veiculoLiveData.value = Converter(response.body()!!.data.toString())
                    } else {
                        Log.d("VeiculoViewModel",response.message())
                    }
                }

                override fun onFailure(call: Call<Result>, t: Throwable) {
                    Log.d("VeiculoViewModel", t.message.toString())
                }
            })
    }

    fun observeVeiculoLiveData(): LiveData<Veiculo> {
        Log.d("VeiculoViewModel" , "teste 10")
        return veiculoLiveData
    }

    fun Converter(str: String) : Veiculo {
        Log.d("VeiculoViewModel","json ${str}")
        var json: JSONObject
        try {
            json = JSONObject(str)
            Log.d("VeiculoViewModel",json.toString())
            return json as Veiculo
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return Veiculo(
            veiculoId = 0,
            marca =  "",
            modelo = "",
            cor = "",
            tipo = "",
            anoFabricacao = 0,
            anoModelo = 0,
            combustivel = "",
            placa = "",
            renavam = "",
            chassi = "",
            acessorio = "",
            observacao = ""
        )
    }
}