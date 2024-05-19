package com.brunobatista.trabalho_3_1.model

import java.util.Date

data class Grupo(
    val grupoId: Int,
    val descricao: String,
    val id: String,
    val subjectOwner: String,
    val subjectTime: Date,
    val dataCriacao: Date,
    val observacao: String,
    val descId: String,
    val criador: String
)
