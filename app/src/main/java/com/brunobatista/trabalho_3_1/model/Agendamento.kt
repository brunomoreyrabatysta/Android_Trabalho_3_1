package com.brunobatista.trabalho_3_1.model

import java.util.Date

data class Agendamento(
    val agendamentoId: Int,
    val diaSemana: Int,
    val dataAgendamento: Date,
    val dataInicio: Date,
    val dataTermino: Date,
    val tipoMidia: Int,
    val urlMidia: String,
    val mensagem: String
)
