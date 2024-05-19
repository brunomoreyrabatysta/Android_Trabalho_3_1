package com.brunobatista.trabalho_3_1.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.brunobatista.trabalho_3_1.databinding.ActivityLoginBinding
import com.brunobatista.trabalho_3_1.ui.usuario.UsuarioListActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.btnLogin.setOnClickListener{
            val usuario = binding.edtUsuario.text.toString().trim()
            val senha = binding.edtSenha.text.toString().trim()

            loginViewModel.performLogin(usuario, senha)
        }

        loginViewModel.loginResultLiveData.observe(this, Observer { ehSucesso ->
            if (ehSucesso) {
                Log.e("TESTE","LOGIN OK")
                val intent = Intent(this, UsuarioListActivity::class.java)
                startActivity(intent)
            } else {
                Log.e("TESTE","Usuário ou senha inválido")
                Toast.makeText(this, "Usuario ou senha inválidos", Toast.LENGTH_LONG).show()
            }
        })
    }
}