package com.brunobatista.trabalho_3_1.ui.veiculo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.brunobatista.trabalho_3_1.databinding.ActivityVeiculoListBinding

class VeiculoListActivity : AppCompatActivity(), VeiculoAdapter.OnDeleteClickListener, VeiculoAdapter.OnUpdateClickListener {
    private lateinit var binding: ActivityVeiculoListBinding
    private lateinit var veiculoAdapter: VeiculoAdapter
    private lateinit var viewModel: VeiculoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVeiculoListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CarregarVeiculos()
        viewModel = ViewModelProvider(this)[VeiculoViewModel::class.java]
        viewModel.getVeiculoAll()
        viewModel.observeListVeiculoLiveData().observe(this, Observer { veiculoList ->
            veiculoAdapter.setVeiculoList(veiculoList)
        })

        binding.btnAdicionar.setOnClickListener{
            val intent = Intent(this, VeiculoCadastroActivity::class.java)
            intent.putExtra("KEY_VEICULOID", "");
            startActivity(intent);
        }
    }

    fun CarregarVeiculos() {
        veiculoAdapter = VeiculoAdapter(this, this)
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(applicationContext, 1)
            adapter = veiculoAdapter
        }
    }

    override fun onDeleteClick(veiculoId: Int) {
        Excluir(veiculoId)
    }

    override fun onUpdateClick(veiculoId: Int) {
        val intent = Intent(this, VeiculoCadastroActivity::class.java)
        intent.putExtra("KEY_VEICULOID", veiculoId.toString());
        startActivity(intent);
    }

    fun Excluir(veiculoId: Int) {
        viewModel = ViewModelProvider(this)[VeiculoViewModel::class.java]

        viewModel.DeleteVeiculo(veiculoId.toInt())
        viewModel.observeVeiculoLiveData().observe(this, Observer { veiculo ->
            if (veiculo == null) {
                Toast.makeText(this, "Veículo não cadastrado!", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, "Veículo excluído com sucesso!", Toast.LENGTH_LONG).show()
            }
        })
    }
}