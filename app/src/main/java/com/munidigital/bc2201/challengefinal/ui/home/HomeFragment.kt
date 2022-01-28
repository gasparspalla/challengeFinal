package com.munidigital.bc2201.challengefinal.ui.home

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.munidigital.bc2201.challengefinal.api.ApiResponseStatus
import com.munidigital.bc2201.challengefinal.FavoriteTeam
import com.munidigital.bc2201.challengefinal.databinding.FragmentHomeBinding
import com.munidigital.bc2201.challengefinal.interfaces.SetOnItemListener

class HomeFragment() : Fragment(){



    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var itemSelectListener: SetOnItemListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        itemSelectListener = try {
            context as SetOnItemListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement PokemonSelectListener")
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val rootView = binding.root

        viewModel=ViewModelProvider(this,MainViewModelFactory(requireActivity().application)).get(HomeViewModel::class.java)

        val recycler=binding.teamRecycler
        recycler.layoutManager = LinearLayoutManager(requireActivity())

        val adapter = SoccerAdapter(requireActivity())
        recycler.adapter = adapter


        observerTeamList(adapter)
        observerStateCharge()

        adapter.onItemClickListener={
            itemSelectListener.onTeamSelected(it)
        }

        adapter.onItemFavoriteClickListener={
            val favorite= FavoriteTeam(it.idTeam,it.nameTeam,it.imageUrl)
            itemSelectListener.onFavoriteSelected(favorite)
        }


        binding.txtBuscar.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            @RequiresApi(Build.VERSION_CODES.N)
            override fun onQueryTextChange(txtBuscar:String): Boolean {
                adapter.filter(txtBuscar)
                return false
            }
        })



        return rootView
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
            adapter.completeList(it)
            if (it.isEmpty()){
                binding.tvNoTeam.visibility = View.VISIBLE
                binding.progressBar.visibility=View.GONE
            }
            else binding.tvNoTeam.visibility=View.GONE
        }
    }



}