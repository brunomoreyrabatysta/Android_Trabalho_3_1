package com.brunobatista.trabalho_3_1.ui.usuario

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brunobatista.trabalho_3_1.databinding.UsuarioItemBinding
import com.brunobatista.trabalho_3_1.model.Usuario


class UsuarioAdapter(
    _onDeleteClickListener: OnDeleteClickListener,
    _onUpdateClickListener: OnUpdateClickListener
) : RecyclerView.Adapter<UsuarioAdapter.ViewHolder>() {
    private var usuarioList = ArrayList<Usuario>()

    private var onDeleteClickListener: OnDeleteClickListener? = _onDeleteClickListener
    private var onUpdateClickListener: OnUpdateClickListener? = _onUpdateClickListener

    fun setUsuarioList(usuarioList: List<Usuario>) {
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
