package com.brunobatista.trabalho_3_1.ui.usuario

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.brunobatista.trabalho_3_1.databinding.ActivityUsuarioListBinding


class UsuarioListActivity : AppCompatActivity(), UsuarioAdapter.OnDeleteClickListener, UsuarioAdapter.OnUpdateClickListener  {
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
            usuarioAdapter.setUsuarioList(usuarioList)
        })

        binding.btnAdicionar.setOnClickListener{
            val intent = Intent(this, UsuarioCadastroActivity::class.java)
            intent.putExtra("KEY_USUARIOID", "");
            startActivity(intent);
        }
    }

    fun CarregarUsuario() {
        usuarioAdapter = UsuarioAdapter(this, this)
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(applicationContext, 1)
            adapter = usuarioAdapter
        }
    }

    override fun onDeleteClick(usuarioId: Int) {
        Excluir(usuarioId)
    }

    override fun onUpdateClick(usuarioId: Int) {
        val intent = Intent(this, UsuarioCadastroActivity::class.java)
        intent.putExtra("KEY_USUARIOID", usuarioId.toString());
        startActivity(intent);
    }

    fun Excluir(usuarioId: Int) {
        viewModel = ViewModelProvider(this)[UsuarioViewModel::class.java]

        viewModel.DeleteUsuario(usuarioId.toInt())
        viewModel.observeUsuarioLiveData().observe(this, Observer { usuario ->
            if (usuario == null) {
                Toast.makeText(this, "Usuário não cadastrado!", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, "Usuário excluído com sucesso!", Toast.LENGTH_LONG).show()
            }
        })
    }
}