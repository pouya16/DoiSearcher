package com.example.doisearcher

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doisearcher.adapters.DoisAdapter
import com.example.doisearcher.databinding.FragmentSecondBinding
import com.example.doisearcher.viewModels.SharedViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    lateinit var name:String

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val sharedViewModel: SharedViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        name = arguments?.getString("name").toString()
        Log.i("Log1","name is : " + name)
        binding.txtClient.text = name
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.doiList.observe(viewLifecycleOwner, Observer {
            val filteredList = it.filter { it.relationships.client.data.id == name }
            val adapter = DoisAdapter(filteredList)
            binding.recycler.layoutManager = LinearLayoutManager(requireContext())
            binding.recycler.adapter = adapter
        })
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}