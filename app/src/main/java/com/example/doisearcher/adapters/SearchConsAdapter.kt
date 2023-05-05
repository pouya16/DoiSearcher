package com.example.doisearcher.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.doisearcher.R
import com.example.doisearcher.databinding.ListItemSearchBinding
import com.example.doisearcher.datamodels.Item


interface SearchConsClickListener{
    fun onItemClicked(name:String)
}


class SearchConsAdapter(private val itemList: List<Item>/*,private val listener:SearchConsClickListener*/) : RecyclerView.Adapter<SearchConsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }

    inner class ViewHolder(private val binding: ListItemSearchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.clientId.text = item.name
            binding.repeat.text = item.count.toString()

            binding.root.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("name",binding.clientId.text.toString())
                binding.root.findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,bundle)
            }
        }
    }
}