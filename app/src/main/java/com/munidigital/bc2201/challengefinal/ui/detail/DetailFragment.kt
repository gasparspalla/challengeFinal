package com.munidigital.bc2201.challengefinal.ui.detail

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.graphics.rotationMatrix
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.munidigital.bc2201.challengefinal.ui.map.MapsActivity
import com.munidigital.bc2201.challengefinal.ui.map.MapsActivity.Companion.LOCATION_KEY
import com.munidigital.bc2201.challengefinal.R
import com.munidigital.bc2201.challengefinal.TeamArg
import com.munidigital.bc2201.challengefinal.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private val args:DetailFragmentArgs by navArgs()
    private lateinit var viewModel: DetailViewModel
    private lateinit var binding:FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        binding = FragmentDetailBinding.inflate(inflater, container, false)
        val rootView = binding.root

        val team=args.team

        setData(team)

        binding.btnMap.setOnClickListener {
            callMapsActivity(team.nameStadium)
        }


        binding.btnWebsite.setOnClickListener {
            val website=args.team.webSite
            if (website.isNotBlank()){
                val url=Uri.parse(website)
                val intent=Intent(Intent.ACTION_VIEW,url)
                startActivity(intent)
            }
            else{
                showAlertWebsite(requireContext())
            }
        }


        return rootView
    }


    private fun callMapsActivity(location:String) {
        if (location.isBlank()){
            showAlertMap(requireContext())
        }
        else{
            val intent=Intent(requireContext(), MapsActivity::class.java)
            intent.putExtra(LOCATION_KEY,location)
            startActivity(intent)
        }
    }

    private fun setData(team: TeamArg) {
        Glide.with(this).load(team.imageUrl).listener(object: RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                binding.detailimgTeam.setImageResource(R.drawable.image_not_supported)
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

        }).error(R.drawable.image_not_supported).into(binding.detailimgTeam)


        binding.detailTvNameTeam.text= getString(R.string.name_team,team.nameTeam?:noInformation())
        binding.detailTvAlternateTeam.text= getString(R.string.name_allternate_team,team.nameAlternateTeam?:noInformation())
        binding.detailTvNameLeague.text= getString(R.string.name_league,team.nameLeague?:noInformation())
        binding.detailNameStadium.text= getString(R.string.name_stadium,team.nameStadium?:noInformation())
        binding.detailNameStadiumLocation.text= getString(R.string.ubication_stadium,team.nameStadiumLocation?:noInformation())
        binding.detailDescription.text= getString(R.string.description,team.description?:noInformation())


    }

    private fun noInformation():String{
        return getString(R.string.no_information)
    }

    fun showAlertMap(context: Context) {
        val builder= AlertDialog.Builder(context)
        builder.setTitle(context.getString(R.string.showAlertError))
        builder.setMessage(context.getString(R.string.stadium_location_error))
        builder.setPositiveButton("OK",null)
        val dialog: AlertDialog =builder.create()
        dialog.show()
    }

    fun showAlertWebsite(context: Context) {
        val builder= AlertDialog.Builder(context)
        builder.setTitle(context.getString(R.string.showAlertError))
        builder.setMessage(context.getString(R.string.website_error))
        builder.setPositiveButton("OK",null)
        val dialog: AlertDialog =builder.create()
        dialog.show()
    }


}