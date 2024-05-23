package com.brunobatista.trabalho_3_1.ui.veiculo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brunobatista.trabalho_3_1.databinding.VeiculoItemBinding
import com.brunobatista.trabalho_3_1.model.Veiculo

class VeiculoAdapter(
    _onDeleteClickListener: OnDeleteClickListener,
    _onUpdateClickListener: OnUpdateClickListener
) : RecyclerView.Adapter<VeiculoAdapter.ViewHolder>() {
    private var veiculoList = ArrayList<Veiculo>()

    private var onDeleteClickListener: OnDeleteClickListener? = _onDeleteClickListener
    private var onUpdateClickListener: OnUpdateClickListener? = _onUpdateClickListener

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

        holder.binding.btnExcluir.setOnClickListener {
            onDeleteClickListener?.onDeleteClick(veiculo.veiculoId)
        };

        holder.binding.btnAlterar.setOnClickListener {
            onUpdateClickListener?.onUpdateClick(veiculo.veiculoId)
        };
    }

    interface OnDeleteClickListener {
        fun onDeleteClick(veiculoId: Int)
    }

    interface OnUpdateClickListener {
        fun onUpdateClick(veiculoId: Int)
    }
}