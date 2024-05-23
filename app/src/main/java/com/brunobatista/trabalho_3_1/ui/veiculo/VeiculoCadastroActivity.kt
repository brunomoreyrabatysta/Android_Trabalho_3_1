package com.brunobatista.trabalho_3_1.ui.veiculo

import android.os.Bundle
import android.view.View
import android.widget.Toast
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
        binding = ActivityVeiculoCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var strVeiculo: String? = intent.getStringExtra("KEY_VEICULOID")

        if (strVeiculo != null) {
            veiculoId = strVeiculo
        } else {
            veiculoId = ""
        }

        novoRegistro = veiculoId.isEmpty();

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
            viewModel = ViewModelProvider(this)[VeiculoViewModel::class.java]

            viewModel.getVeiculoById(veiculoId.toInt())
            viewModel.observeVeiculoLiveData().observe(this, Observer { veiculo ->
                if (veiculo == null) {
                    Toast.makeText(this, "Veículo não cadastrado!", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    set(veiculo)
                }
            })
        }

        binding.btnSalvar.setOnClickListener(View.OnClickListener { Salvar() })
        binding.btnExcluir.setOnClickListener(View.OnClickListener { Excluir() })
    }

    fun set(veiculo: Veiculo) {
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
        binding.edtAnoFabricacao.setText(anoFabricacao.toString())
        binding.edtAnoModelo.setText(anoModelo.toString())
        binding.edtPlaca.setText(placa)
        binding.edtRenavam.setText(renavam)
        binding.edtChassi.setText(chassi)
    }

    fun Salvar() {
        if (Validar()) {
            viewModel = ViewModelProvider(this)[VeiculoViewModel::class.java]

            var veiculo: Veiculo
            var mensagem: String

            if (!novoRegistro) {
                veiculo = Veiculo(veiculoId.toInt(),
                    binding.edtMarca.text.toString(),
                    binding.edtModelo.text.toString(),
                    binding.edtCor.text.toString(),
                    "",
                    binding.edtAnoFabricacao.text.toString().toInt(),
                    binding.edtAnoModelo.text.toString().toInt(),
                    "",
                    binding.edtPlaca.text.toString(),
                    binding.edtRenavam.text.toString(),
                    binding.edtChassi.text.toString(),
                    "",
                    "")

                viewModel.UpdateVeiculo(veiculoId.toInt(), veiculo)
                mensagem = "Veículo alterado com sucesso!"
            } else {
                veiculo = Veiculo(0,
                    binding.edtMarca.text.toString(),
                    binding.edtModelo.text.toString(),
                    binding.edtCor.text.toString(),
                    "",
                    binding.edtAnoFabricacao.text.toString().toInt(),
                    binding.edtAnoModelo.text.toString().toInt(),
                    "",
                    binding.edtPlaca.text.toString(),
                    binding.edtRenavam.text.toString(),
                    binding.edtChassi.text.toString(),
                    "",
                    "")

                viewModel.AddVeiculo(veiculo)
                mensagem = "Veículo cadastrado com sucesso!"
            }

            viewModel.observeVeiculoLiveData().observe(this, Observer { veiculo ->
                Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show()
            })
        }
    }

    fun Excluir() {
        viewModel = ViewModelProvider(this)[VeiculoViewModel::class.java]
        viewModel.DeleteVeiculo(veiculoId.toInt())

        viewModel.observeVeiculoLiveData().observe(this, Observer { veiculo ->
            Toast.makeText(this, "Veículo excluído com sucesso!", Toast.LENGTH_LONG).show()
        })
    }

    fun  Validar(): Boolean {

        return true;
    }
}