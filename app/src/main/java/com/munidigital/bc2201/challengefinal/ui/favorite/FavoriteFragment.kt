package com.munidigital.bc2201.challengefinal.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.munidigital.bc2201.challengefinal.FavoriteTeam
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
        val root: View = binding.root

        viewModel=ViewModelProvider(this, FavoriteViewModelFactory(requireActivity().application)).get(
            FavoriteViewModel::class.java)


        val favoriteItem=args.favorite
        favoriteItem?.let { viewModel.setValue(it) }


        val recycler=binding.favoriteRecycler
        recycler.layoutManager = LinearLayoutManager(requireActivity())
        val adapter=FavoriteAdapter(requireActivity())
        recycler.adapter=adapter

        observerListFavorite(adapter)


        return root
    }

    private fun observerListFavorite(adapter: FavoriteAdapter) {

        viewModel.teamListLiveData.observe(requireActivity()){
                adapter.submitList(it)

            }
        }
    }

