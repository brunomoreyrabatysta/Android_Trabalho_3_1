package com.brunobatista.trabalho_3_1.model

import java.sql.Time

data class ConfiguracaoCobranca(
    val configuracaoCobrancaId: Int,
    val tarifaBase: Double,
    val valorMinimo: Double,
    val valorKM: Double,
    val valorMinuto: Double,
    val dinamica: Double,
    val horaInicio: Time,
    val horaTermino: Time,
    val diaInicio: Int,
    val diaTermino: Int,
    val feriado: Int,
    val ordem: Int
)
