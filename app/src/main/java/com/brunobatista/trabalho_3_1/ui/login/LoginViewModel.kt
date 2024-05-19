package com.brunobatista.trabalho_3_1.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    private val USER = "admin"
    private val PASS = "1234"

    val loginResultLiveData = MutableLiveData<Boolean>()

    fun performLogin(usuario: String, senha: String) {
        val isLoginSucesso = usuario == USER && senha == PASS
        loginResultLiveData.value = isLoginSucesso
    }
}