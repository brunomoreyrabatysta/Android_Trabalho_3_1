package com.brunobatista.trabalho_3_1.ui.veiculo

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brunobatista.trabalho_3_1.databinding.UsuarioItemBinding
import com.brunobatista.trabalho_3_1.databinding.VeiculoItemBinding
import com.brunobatista.trabalho_3_1.model.Veiculo
import com.brunobatista.trabalho_3_1.ui.usuario.UsuarioAdapter

class VeiculoAdapter: RecyclerView.Adapter<VeiculoAdapter.ViewHolder>() {
    private var veiculoList = ArrayList<Veiculo>()

    fun setVeiculoList(veiculoList: List<Veiculo>) {
        this.veiculoList = veiculoList as ArrayList<Veiculo>
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: VeiculoItemBinding) :RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return VeiculoAdapter.ViewHolder(VeiculoItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return veiculoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var veiculo = veiculoList[position]

        holder.binding.lblVeiculoId.text = veiculo.veiculoId.toString()
        holder.binding.lblMarca.text = veiculo.marca
        holder.binding.lblModelo.text = veiculo.modelo
        holder.binding.lblPlaca.text =veiculo.placa
    }
}