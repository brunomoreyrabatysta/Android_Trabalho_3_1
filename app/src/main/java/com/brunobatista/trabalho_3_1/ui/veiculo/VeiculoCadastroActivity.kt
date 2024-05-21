package com.brunobatista.trabalho_3_1.ui.veiculo

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.brunobatista.trabalho_3_1.databinding.ActivityVeiculoCadastroBinding
import com.brunobatista.trabalho_3_1.model.Veiculo


class VeiculoCadastroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVeiculoCadastroBinding
    private var veiculoId: String = ""
    private var novoRegistro = false
    private lateinit var viewModel: VeiculoViewModel
    private lateinit var veiculo : Veiculo


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ActivityVeiculoCadastroBinding", "teste1")
        binding = ActivityVeiculoCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var bundle :Bundle ?=intent.extras

        //if (bundle != null ) {
            veiculoId = "2"
        /*
        } else {
            veiculoId = ""
        }
         */

        novoRegistro = veiculoId.isEmpty();

        Log.d("VeiculoCadastroActivity" , "teste 2")
        if (novoRegistro) {
            binding.lblTitulo.setText("NOVO CADASTRO")
            binding.edtMarca.setText("")
            binding.edtModelo.setText("")
            binding.edtCor.setText("")
            binding.edtAnoFabricacao.setText("")
            binding.edtAnoModelo.setText("")
            binding.edtPlaca.setText("")
            binding.edtRenavam.setText("")
            binding.edtChassi.setText("")
            binding.btnExcluir.setVisibility(View.INVISIBLE)
        } else {
            Log.d("VeiculoCadastroActivity" , "teste 3")
            viewModel = ViewModelProvider(this)[VeiculoViewModel::class.java]

            Log.d("VeiculoCadastroActivity" , "teste 4")
            viewModel.getVeiculoById(veiculoId.toInt())
            Log.d("VeiculoCadastroActivity" , "teste 5")
            viewModel.observeVeiculoLiveData().observe(this, Observer { veiculo ->
                Log.d("VeiculoCadastroActivity" , "teste 1")
//                set(veiculo)
            })
        }
    }

    fun setVeiculo(veiculo: Veiculo) {
        this.veiculo = veiculo
        set(veiculo)
    }

    fun set(veiculo: Veiculo) {
        Log.d("VeiculoCadastroActivity" , "teste 6")
        binding.lblTitulo.setText("ALTERAR CADASTRO")
        val marca = veiculo.marca
        val modelo = veiculo.modelo
        val cor = veiculo.cor
        val anoFabricacao = veiculo.anoFabricacao
        val anoModelo = veiculo.anoModelo
        val placa = veiculo.placa
        val renavam = veiculo.renavam
        val chassi = veiculo.chassi
        binding.edtMarca.setText(marca)
        binding.edtModelo.setText(modelo)
        binding.edtCor.setText(cor)
        binding.edtAnoFabricacao.setText(anoFabricacao)
        binding.edtAnoModelo.setText(anoModelo)
        binding.edtPlaca.setText(placa)
        binding.edtRenavam.setText(renavam)
        binding.edtChassi.setText(chassi)
    }
}