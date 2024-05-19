package com.brunobatista.trabalho_3_1.ui.usuario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.brunobatista.trabalho_3_1.databinding.ActivityUsuarioListBinding

class UsuarioListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUsuarioListBinding
    private lateinit var usuarioAdapter: UsuarioAdapter
    private lateinit var viewModel: UsuarioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsuarioListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CarregarUsuario()
        viewModel = ViewModelProvider(this)[UsuarioViewModel::class.java]
        viewModel.getUsuarioAll()
        viewModel.observeListUsuarioLiveData().observe(this, Observer { usuarioList ->
            Log.d("UsuarioListActivity", "CREATE")
            Log.d("UsuarioListActivity", usuarioList.toString())
            usuarioAdapter.setUsuarioList(usuarioList)
        })
    }

    fun CarregarUsuario() {
        usuarioAdapter = UsuarioAdapter()
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(applicationContext, 1)
            adapter = usuarioAdapter
        }
    }
}