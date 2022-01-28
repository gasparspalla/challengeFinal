package com.munidigital.bc2201.challengefinal.ui.detail

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.munidigital.bc2201.challengefinal.R
import com.munidigital.bc2201.challengefinal.TeamArg
import com.munidigital.bc2201.challengefinal.databinding.FragmentDetailBinding

class DetailViewModel : ViewModel() {

    @SuppressLint("StaticFieldLeak")
    private lateinit var context: Context
    private lateinit var binding: FragmentDetailBinding


    fun setContext(requireContext: Context) {
        this.context=requireContext
    }

    fun setBinding(binding: FragmentDetailBinding) {
        this.binding=binding
    }

    fun showAlert(id: Int) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(context.getString(R.string.showAlertError))
        builder.setMessage(context.getString(id))
        builder.setPositiveButton("OK", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


    fun setData(team: TeamArg) {
        Glide.with(context).load(team.imageUrl).listener(object : RequestListener<Drawable> {
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
        binding.detailTvNameTeam.text= context.getString(R.string.name_team,team.nameTeam)

        binding.detailTvNameLeague.text= context.getString(R.string.name_league,let {
            if (team.nameLeague.isEmpty())noInformation()
            else team.nameLeague
        })
        binding.detailNameStadium.text= context.getString(R.string.name_stadium,let {
            if (team.nameStadium.isEmpty())noInformation()
            else team.nameStadium
        })
        binding.detailNameStadiumLocation.text= context.getString(R.string.ubication_stadium,let {
            if (team.nameStadiumLocation.isEmpty())noInformation()
            else team.nameStadiumLocation
        })
        binding.detailDescription.text= context.getString(R.string.description,team.description?:noInformation())
    }


    private fun noInformation():String{
        return context.getString(R.string.no_information)
    }

    fun verificateConnection():Boolean{
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

}