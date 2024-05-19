package com.brunobatista.trabalho_3_1.model

data class Veiculo (
    val veiculoId: Int,
    val marca: String,
    val modelo: String,
    val cor: String,
    val tipo: String,
    val anoFabricacao: Int,
    val anoModelo: Int,
    val combustivel: String,
    val placa: String,
    val renavam: String,
    val chassi: String,
    val acessorio: String,
    val observacao: String
)