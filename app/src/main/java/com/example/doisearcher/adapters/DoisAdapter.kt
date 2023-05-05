package com.example.doisearcher.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.doisearcher.R
import com.example.doisearcher.databinding.ListItemDoisBinding
import com.example.doisearcher.databinding.ListItemSearchBinding
import com.example.doisearcher.datamodels.DataClass
import com.example.doisearcher.datamodels.Item



class DoisAdapter(private val itemList: List<DataClass>/*,private val listener:SearchConsClickListener*/) : RecyclerView.Adapter<DoisAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemDoisBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }

    inner class ViewHolder(private val binding: ListItemDoisBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DataClass) {
            binding.dois.text = item.id
            binding.lang.text = item.attributes.language
            binding.url.text = item.attributes.url
            if(item.attributes.descriptions.isEmpty()){
                binding.txtAbs.text = "no abstract provided"
            }else{
                binding.txtAbs.text = item.attributes.descriptions[0].description
            }

        }
    }
}