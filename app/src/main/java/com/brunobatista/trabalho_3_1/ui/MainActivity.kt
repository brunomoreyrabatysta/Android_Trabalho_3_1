package com.brunobatista.trabalho_3_1.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.brunobatista.trabalho_3_1.databinding.ActivityMainBinding
import com.brunobatista.trabalho_3_1.ui.usuario.UsuarioListActivity
import com.brunobatista.trabalho_3_1.ui.veiculo.VeiculoCadastroActivity
import com.brunobatista.trabalho_3_1.ui.veiculo.VeiculoListActivity


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnUsuario.setOnClickListener{
            CadastroUsuario()
        }

        binding.btnVeiculo.setOnClickListener{
            CadastroVeiculo()
//            EditarVeiculo()
        }

        binding.btnCliente.setOnClickListener{
            Toast.makeText(this, "Cliente em manutenção....", Toast.LENGTH_SHORT).show();
        }

        binding.btnMotorista.setOnClickListener{
            Toast.makeText(this, "Motorista em manutenção....", Toast.LENGTH_SHORT).show()
        }

        binding.btnCorrida.setOnClickListener{
            Toast.makeText(this, "Corrida em manutenção....", Toast.LENGTH_SHORT).show()
        }

        binding.btnMapa.setOnClickListener{
            Toast.makeText(this, "Mapa em manutenção....", Toast.LENGTH_SHORT).show()
        }

        binding.btnConfiguracao.setOnClickListener{
            Toast.makeText(this, "Configuração em manutenção....", Toast.LENGTH_SHORT).show()
        }

        binding.btnSair.setOnClickListener{
            Sair();
        }
    }

    fun Sair() {
        finish()
    }

    fun CadastroUsuario() {
        startActivity(Intent(this, UsuarioListActivity::class.java))
    }

    fun CadastroVeiculo() {
        startActivity(Intent(this, VeiculoListActivity::class.java))
    }

    fun EditarVeiculo() {
        val intent = Intent(this, VeiculoCadastroActivity::class.java)
        intent.putExtra("KEY_VEICULOID", 1);
        startActivity(intent);
    }
}