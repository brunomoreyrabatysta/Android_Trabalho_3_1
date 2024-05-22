package com.brunobatista.trabalho_3_1.model

data class Usuario(
    var usuarioId: Int,
    val nome: String,
    val email: String,
    //val senhaHash: String,
    val senha: String,
    val imagem: String,
    val slug: String,
    val bio: String
)