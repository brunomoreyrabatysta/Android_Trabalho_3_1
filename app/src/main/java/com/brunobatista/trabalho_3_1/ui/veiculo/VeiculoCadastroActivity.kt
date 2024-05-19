package com.brunobatista.trabalho_3_1.ui.veiculo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.brunobatista.trabalho_3_1.databinding.ActivityVeiculoCadastroBinding

class VeiculoCadastroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVeiculoCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVeiculoCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}