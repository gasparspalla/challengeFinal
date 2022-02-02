package com.munidigital.bc2201.challengefinal.ui.favorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.munidigital.bc2201.challengefinal.FavoriteTeam
import com.munidigital.bc2201.challengefinal.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {
    private val args: FavoriteFragmentArgs by navArgs()
    private var favorite:FavoriteTeam?=null
    private val viewModel by viewModels<FavoriteViewModel>()
    private lateinit var binding:FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val rootView= binding.root

//        viewModel=ViewModelProvider(this).get(
//            FavoriteViewModel::class.java)


        if (args.favorite!=null){
            viewModel.setFavoriteData(args.favorite!!)
        }





        val recycler=binding.favoriteRecycler
        recycler.layoutManager = LinearLayoutManager(requireActivity())

        val adapter=FavoriteAdapter(requireActivity())
        recycler.adapter=adapter

        observerListFavorite(adapter)


        return rootView
    }


    private fun observerListFavorite(adapter: FavoriteAdapter) {
        viewModel.teamListLiveData.observe(requireActivity()) {
            adapter.submitList(it)
            if (it.isEmpty()) binding.tvNoFavorite.visibility = View.VISIBLE
            else binding.tvNoFavorite.visibility=View.GONE

        }
    }
}

