package com.brunobatista.trabalho_3_1.model

import java.util.Date

data class Cliente (
    val clienteId: Int,
    val nome: String,
    val telefone: String,
    val telefoneCompleto: String,
    val foto: String,
    val dataCadastro: Date,
    val descricaoPerfil: String,
    val descricaoStatus: String
)