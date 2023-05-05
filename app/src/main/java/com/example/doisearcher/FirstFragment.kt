package com.example.doisearcher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doisearcher.adapters.SearchConsAdapter
import com.example.doisearcher.databinding.FragmentFirstBinding
import com.example.doisearcher.datamodels.Item
import com.example.doisearcher.viewModels.ConsApiStatus
import com.example.doisearcher.viewModels.SharedViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    lateinit var uniqueList: List<Item>
    /*private val adapterClickListener = object : SearchConsClickListener {
        override fun onItemClicked(name: String) {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

    }*/
    private var _binding: FragmentFirstBinding? = null
    private val sharedViewModel: SharedViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSearch.setOnClickListener{
            if(binding.searchQuery.text.toString().length > 0){
                sharedViewModel.getCons(binding.searchQuery.text.toString())
            }else{
                Toast.makeText(requireContext(),"fill search query",Toast.LENGTH_SHORT)
            }
        }


        sharedViewModel.status.observe(viewLifecycleOwner, Observer {
            value -> setStat(value)
        })

        sharedViewModel.doiList.observe(viewLifecycleOwner, Observer {
            val groupedMap = it.groupingBy { it.relationships.client.data.id }.eachCount()
            uniqueList = groupedMap.entries.map{
                (id,count) -> Item(id,count)
            }
            val searchConsAdapter = SearchConsAdapter(uniqueList/*,adapterClickListener*/)
            binding.recycler.layoutManager = LinearLayoutManager(requireContext())
            binding.recycler.adapter = searchConsAdapter
        })

        /*binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }*/
    }

    private fun setStat(value:ConsApiStatus){
        binding.progress.visibility = View.GONE
        binding.txtError.visibility = View.GONE
        binding.btnSearch.isActivated = true
        when(value){
            ConsApiStatus.ERROR -> {
                Toast.makeText(requireContext(),"error retrieving data", Toast.LENGTH_SHORT)
            }
            ConsApiStatus.DONE ->{
            }
            ConsApiStatus.NO_DATA ->{
                binding.txtError.visibility = View.VISIBLE

            }
            ConsApiStatus.LOADING ->{
                binding.progress.visibility = View.VISIBLE
                binding.btnSearch.isActivated = false
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}