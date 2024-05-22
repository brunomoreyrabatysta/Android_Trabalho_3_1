package com.brunobatista.trabalho_3_1.ui.usuario

import android.health.connect.datatypes.units.Length
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.brunobatista.trabalho_3_1.databinding.ActivityUsuarioCadastroBinding
import com.brunobatista.trabalho_3_1.model.Usuario


class UsuarioCadastroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUsuarioCadastroBinding
    private var usuarioId: String = ""
    private var novoRegistro = false
    private lateinit var viewModel: UsuarioViewModel
    private lateinit var usuario : Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsuarioCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //var bundle :Bundle ?=intent.extras
        Log.d("UsuarioCadastroActivity", "teste 1")
        //var message = bundle!!.getString("KEY_USUARIOID")
        //Log.d("UsuarioCadastroActivity", "teste 1 - ${ message }")
        var strUser: String? = intent.getStringExtra("KEY_USUARIOID")
        Log.d("UsuarioCadastroActivity", "teste 1 - ${ strUser }")

        if (strUser != null) {
            usuarioId = strUser
        } else {
            usuarioId = ""
        }

        novoRegistro = usuarioId.isEmpty();

        if (novoRegistro) {
            binding.lblTitulo.setText("NOVO CADASTRO")
            binding.edtUsuarioId.setText("")
            binding.edtUsuarioId.setVisibility(View.INVISIBLE)
            binding.edtNome.setText("")
            binding.edtEmail.setText("")
            binding.edtSenha.setText("")
            binding.edtConfirmacaoSenha.setText("")
            binding.btnExcluir.setVisibility(View.INVISIBLE)
        } else {
            viewModel = ViewModelProvider(this)[UsuarioViewModel::class.java]

            viewModel.getUsuarioById(usuarioId.toInt())
            viewModel.observeUsuarioLiveData().observe(this, Observer { usuario ->
                if (usuario == null) {
                    Toast.makeText(this, "Usuário não cadastrado!", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    set(usuario)
                }
            })
        }

        binding.btnSalvar.setOnClickListener(View.OnClickListener { Salvar() })
        binding.btnExcluir.setOnClickListener(View.OnClickListener { Excluir() })
    }

    fun set(usuario: Usuario) {
        binding.lblTitulo.setText("ALTERAR CADASTRO")

        binding.edtUsuarioId.setText(usuario.usuarioId.toString())
        binding.edtNome.setText(usuario.nome)
        binding.edtEmail.setText(usuario.email)
        binding.edtSenha.setText("")
        binding.edtConfirmacaoSenha.setText("")
    }

    fun Salvar() {
        if (Validar()) {
            viewModel = ViewModelProvider(this)[UsuarioViewModel::class.java]

            var usuario: Usuario
            var mensagem: String

            if (!novoRegistro) {
                usuario = Usuario(usuarioId.toInt(),
                    binding.edtNome.text.toString(),
                    binding.edtEmail.text.toString(),
                    binding.edtSenha.text.toString(),
                    "",
                    "",
                    "")

                viewModel.UpdateUsuario(usuarioId.toInt(), usuario)
                mensagem = "Usuário alterado com sucesso!"
            } else {
                usuario = Usuario(0,
                    binding.edtNome.text.toString(),
                    binding.edtEmail.text.toString(),
                    binding.edtSenha.text.toString(),
                    "",
                    "",
                    "")

                viewModel.AddUsuario(usuario)
                mensagem = "Usuário cadastrado com sucesso!"
            }

            viewModel.observeUsuarioLiveData().observe(this, Observer { usuario ->
                Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show()
            })
        }
    }

    fun Excluir() {
        viewModel = ViewModelProvider(this)[UsuarioViewModel::class.java]
        viewModel.DeleteUsuario(usuarioId.toInt())

        viewModel.observeUsuarioLiveData().observe(this, Observer { usuario ->
            Toast.makeText(this, "Usuário excluído com sucesso!", Toast.LENGTH_LONG).show()
        })
    }

    fun  Validar(): Boolean {

        return true;
    }
}