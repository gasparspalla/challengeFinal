package com.munidigital.bc2201.challengefinal.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.munidigital.bc2201.challengefinal.FavoriteTeam
import com.munidigital.bc2201.challengefinal.IFragment
import com.munidigital.bc2201.challengefinal.MainActivity
import com.munidigital.bc2201.challengefinal.TeamArg
import com.munidigital.bc2201.challengefinal.api.ApiResponseStatus
import com.munidigital.bc2201.challengefinal.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val activity=(activity as MainActivity)
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel=ViewModelProvider(this,MainViewModelFactory(requireActivity().application)).get(HomeViewModel::class.java)

        val recycler=binding.teamRecycler
        recycler.layoutManager = LinearLayoutManager(requireActivity())

        val adapter = SoccerAdapter(requireActivity())
        recycler.adapter = adapter

        observerTeamList(adapter)
        observerStateCharge()

        adapter.onItemClickListener={
            activity.setTeamSelected(it)
        }
        adapter.onItemFavoriteClickListener={
            val favoriteTeam=FavoriteTeam(it.idTeam,it.nameTeam,it.imageUrl)
            activity.setFavoriteItem(favoriteTeam)
        }

        return root
    }



    private fun observerStateCharge() {
        val progressBar=binding.progressBar
        viewModel.statusLiveData.observe(requireActivity()){
            when{(it== ApiResponseStatus.LOADING)->progressBar.visibility = View.VISIBLE

                (it== ApiResponseStatus.DONE)->progressBar.visibility= View.GONE

                (it== ApiResponseStatus.NO_INTERNET_CONNECTION)->progressBar.visibility= View.GONE

            }
        }
    }

    private fun observerTeamList(adapter:SoccerAdapter) {
        viewModel.teamListLiveData.observe(requireActivity()) {
            adapter.submitList(it)
        }
    }
}