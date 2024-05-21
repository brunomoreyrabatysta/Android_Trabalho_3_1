package com.brunobatista.trabalho_3_1.model

data class User(
    val data: List<Users>,
    val errors: List<Any>
)

data class Users (
    val bio: Any,
    val email: String,
    val imagem: Any,
    val nome: String,
    val slug: String,
    val usuarioId: Int
)