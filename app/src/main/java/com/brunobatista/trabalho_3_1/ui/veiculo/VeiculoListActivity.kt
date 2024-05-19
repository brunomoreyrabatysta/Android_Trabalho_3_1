package com.brunobatista.trabalho_3_1.ui.veiculo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.brunobatista.trabalho_3_1.R
import com.brunobatista.trabalho_3_1.databinding.ActivityUsuarioListBinding
import com.brunobatista.trabalho_3_1.databinding.ActivityVeiculoListBinding
import com.brunobatista.trabalho_3_1.ui.usuario.UsuarioAdapter
import com.brunobatista.trabalho_3_1.ui.usuario.UsuarioViewModel

class VeiculoListActivity : AppCompatActivity() {
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
    }

    fun CarregarVeiculos() {
        veiculoAdapter = VeiculoAdapter()
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(applicationContext, 1)
            adapter = veiculoAdapter
        }
    }
}