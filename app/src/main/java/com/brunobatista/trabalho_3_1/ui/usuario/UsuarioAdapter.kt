package com.brunobatista.trabalho_3_1.ui.usuario

import android.R.id.message
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brunobatista.trabalho_3_1.databinding.UsuarioItemBinding
import com.brunobatista.trabalho_3_1.model.Users
import com.brunobatista.trabalho_3_1.model.Usuario
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.Arrays


class UsuarioAdapter : RecyclerView.Adapter<UsuarioAdapter.ViewHolder>() {
    private var usuarioList = ArrayList<Users>()

    fun setUsuarioList(usuarioList: List<Users>) {
        Log.d("UsuarioAdapter", "setUsuarioList")
        this.usuarioList = usuarioList as ArrayList<Users>
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: UsuarioItemBinding) :RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(UsuarioItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return usuarioList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Problema ao fazer a conversão

        Log.d("UsuarioAdapter", "onBindViewHolder")
        Log.d("UsuarioAdapter", "Usuarios ${usuarioList.toString()}")
        Log.d("UsuarioAdapter", "Posição ${position.toString()}")

        Log.d("UsuarioAdapter", "Teste 1")

        /*
        val usuario: Usuario = usuarioList.get(position)

        Log.d("UsuarioAdapter", "Usuário- [${position}] - ${usuario.toString()}")
         */

        holder.binding.lblNome.text = usuarioList.get(position).nome
        holder.binding.lblUsuarioId.text = usuarioList.get(position).usuarioId.toString()
        holder.binding.lblEmail.text = usuarioList.get(position).email
    }

    inline fun <reified T> parseArray(json: String, typeToken: Type): T {
        val gson = GsonBuilder().create()
        return gson.fromJson<T>(json, typeToken)
    }
}