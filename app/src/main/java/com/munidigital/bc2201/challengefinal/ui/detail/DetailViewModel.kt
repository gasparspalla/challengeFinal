package com.munidigital.bc2201.challengefinal.ui.detail

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.munidigital.bc2201.challengefinal.R
import com.munidigital.bc2201.challengefinal.TeamArg
import com.munidigital.bc2201.challengefinal.databinding.FragmentDetailBinding

class DetailViewModel : ViewModel() {

//    fun viewLinks(team: TeamArg){
//        when{
//            (team.facebook.isNotBlank())->getFacebook(team.facebook)
//
//            (team.instagram.isNotBlank())->getInstagram(team.instagram)
//
//            (team.twitter.isNotBlank())->getTwitter(team.twitter)
//
//            (team.webSite.isNotBlank())->getWebsite(team.webSite)
//        }
//    }
//
//    private fun getWebsite(webSite: String):String {
//        return webSite
//    }
//
//    private fun getTwitter(twitter: String):String {
//        return  twitter
//    }
//
//    fun getInstagram(instagram: String):String{
//        return instagram
//    }
//
//    fun getFacebook(facebook:String):String{
//        return facebook
//    }


}