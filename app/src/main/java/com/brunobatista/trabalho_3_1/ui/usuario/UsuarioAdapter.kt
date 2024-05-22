package com.brunobatista.trabalho_3_1.ui.usuario

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brunobatista.trabalho_3_1.databinding.UsuarioItemBinding
import com.brunobatista.trabalho_3_1.model.Usuario
import com.google.gson.GsonBuilder
import java.lang.reflect.Type


class UsuarioAdapter(
    usuarioListActivity: UsuarioListActivity,
    usuarioListActivity1: UsuarioListActivity
) : RecyclerView.Adapter<UsuarioAdapter.ViewHolder>() {
    private var usuarioList = ArrayList<Usuario>()

    private var onDeleteClickListener: OnDeleteClickListener? = null
    private var onUpdateClickListener: OnUpdateClickListener? = null

    fun UsuarioAdapter(_onDeleteClickListener: OnDeleteClickListener?,
                        _onUpdateClickListener: OnUpdateClickListener?) {
        onDeleteClickListener = _onDeleteClickListener
        onUpdateClickListener = _onUpdateClickListener
    }

    fun setUsuarioList(usuarioList: List<Usuario>) {
        Log.d("UsuarioAdapter", "setUsuarioList")
        this.usuarioList = usuarioList as ArrayList<Usuario>
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
        val usuario: Usuario = usuarioList.get(holder.getAdapterPosition())

        holder.binding.lblNome.text = usuario.nome
        holder.binding.lblUsuarioId.text = usuario.usuarioId.toString()
        holder.binding.lblEmail.text = usuario.email

        holder.binding.btnExcluir.setOnClickListener {
            onDeleteClickListener?.onDeleteClick(usuario.usuarioId)
        };

        holder.binding.btnAlterar.setOnClickListener {
            onUpdateClickListener?.onUpdateClick(usuario.usuarioId)
        };
    }

    interface OnDeleteClickListener {
        fun onDeleteClick(usuarioId: Int)
    }


    interface OnUpdateClickListener {
        fun onUpdateClick(usuarioId: Int)
    }
}
