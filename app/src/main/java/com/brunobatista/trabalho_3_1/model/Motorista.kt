package com.brunobatista.trabalho_3_1.model

import java.util.Date

data class Motorista(
    val motoristaId: Int,
    val nome: String,
    val email: String,
    val dataNascimento: Date,
    val logradouro: String,
    val complemento: String,
    val numero: String,
    val bairro: String,
    val municipio: String,
    val uf: String,
    val cep: String,
    val rg: String,
    val dataExpedicaoRG: Date,
    val orgaoExpedidorRG: String,
    val ufOrgaoExpedidorRG: String,
    val cpf: String,
    val numeroHabilitacao: String,
    val dataValidadeCNH: Date,
    val dataEmissaoCNH: Date,
    val dataPrimeiraEmissaoCNH: Date,
    val ufOrgaoEmissaoCNH: String,
    val categoriaCNH: String,
    val earCNH: Int
)
