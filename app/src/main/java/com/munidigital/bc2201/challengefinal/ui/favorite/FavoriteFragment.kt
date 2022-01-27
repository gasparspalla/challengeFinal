package com.munidigital.bc2201.challengefinal.ui.favorite

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.munidigital.bc2201.challengefinal.FavoriteTeam
import com.munidigital.bc2201.challengefinal.R
import com.munidigital.bc2201.challengefinal.SetOnItemListener
import com.munidigital.bc2201.challengefinal.databinding.FragmentFavoriteBinding
import com.munidigital.bc2201.challengefinal.ui.detail.DetailFragmentArgs
import com.munidigital.bc2201.challengefinal.ui.home.HomeViewModel
import com.munidigital.bc2201.challengefinal.ui.home.MainViewModelFactory

class FavoriteFragment : Fragment() {
    private val args: FavoriteFragmentArgs by navArgs()
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var binding:FragmentFavoriteBinding



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val rootView= binding.root

        viewModel=ViewModelProvider(this, FavoriteViewModelFactory(requireActivity().application)).get(
            FavoriteViewModel::class.java)


        val favoriteItem=args.favorite
        favoriteItem?.let { viewModel.setFavoriteData(it) }


        val recycler=binding.favoriteRecycler
        recycler.layoutManager = LinearLayoutManager(requireActivity())
        val adapter=FavoriteAdapter(requireActivity())
        recycler.adapter=adapter

        observerListFavorite(adapter)




        return rootView
    }

//    fun setFavoriteData(){
//        val favoriteItem=args.favorite
//        favoriteItem?.let { viewModel.setFavoriteData(it) }    }

    private fun observerListFavorite(adapter: FavoriteAdapter) {

        viewModel.teamListLiveData.observe(requireActivity()) {
            adapter.submitList(it)
            if (it.isEmpty()) binding.tvNoFavorite.visibility = View.VISIBLE
            else binding.tvNoFavorite.visibility=View.GONE

        }
    }
}

