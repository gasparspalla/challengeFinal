package com.munidigital.bc2201.challengefinal.ui.detail

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.engine.Initializable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.munidigital.bc2201.challengefinal.activities.MapsActivity
import com.munidigital.bc2201.challengefinal.activities.MapsActivity.Companion.LOCATION_KEY
import com.munidigital.bc2201.challengefinal.R
import com.munidigital.bc2201.challengefinal.TeamArg
import com.munidigital.bc2201.challengefinal.activities.LoginActivity
import com.munidigital.bc2201.challengefinal.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private val args:DetailFragmentArgs by navArgs()
    private lateinit var viewModel: DetailViewModel
    private lateinit var binding:FragmentDetailBinding
    private lateinit var team:TeamArg

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        val rootView = binding.root

        viewModel.setContext(requireContext())
        viewModel.setBinding(binding)

        team=args.team

        viewModel.setData(team)

        binding.btnMap.setOnClickListener {
            callMapsActivity(team.nameStadiumLocation)
        }


        binding.btnWebsite.setOnClickListener {
            openWebsite(team)
        }


        return rootView
    }

    private fun openWebsite(team:TeamArg) {
        if (team.webSite.isNotBlank()){
            val website="https://${team.webSite}"
            val url=Uri.parse(website)
            val intent=Intent(Intent.ACTION_VIEW,url)
            startActivity(intent)
        }
        else{
            viewModel.showAlert(R.string.website_error)
        }
    }


    private fun callMapsActivity(location:String) {
            if (location.isBlank()){
                viewModel.showAlert(R.string.stadium_location_error)
            }
            else{
                val intent=Intent(requireContext(), MapsActivity::class.java)
                intent.putExtra(LOCATION_KEY,location)
                startActivity(intent)
            }

    }



}