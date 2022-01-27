package com.munidigital.bc2201.challengefinal.ui.favorite

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.munidigital.bc2201.challengefinal.FavoriteTeam
import com.munidigital.bc2201.challengefinal.TeamArg
import com.munidigital.bc2201.challengefinal.databinding.FavoriteListItemBinding
import android.R
import android.content.Context
import android.view.View

import android.widget.TextView

import androidx.annotation.NonNull

import com.munidigital.bc2201.challengefinal.MainActivity
import com.munidigital.bc2201.challengefinal.databinding.TeamListItemBinding


class FavoriteAdapter(private val activity: Activity):
    ListAdapter<FavoriteTeam, FavoriteAdapter.FavoriteViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<FavoriteTeam>() {
        override fun areItemsTheSame(oldItem: FavoriteTeam, newItem: FavoriteTeam): Boolean {
            return oldItem.idFavoriteTeam == newItem.idFavoriteTeam
        }

        override fun areContentsTheSame(oldItem: FavoriteTeam, newItem: FavoriteTeam): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = FavoriteListItemBinding.inflate(LayoutInflater.from(parent.context))
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val favorite: FavoriteTeam = getItem(position)
        holder.bind(favorite)
    }


    inner class FavoriteViewHolder(private val binding: FavoriteListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(favorite: FavoriteTeam) {
            binding.favoriteTvNameLong.text=favorite.nameFavoriteTeam
            Glide.with(activity).load(favorite.imgFavorite).into(binding.favoriteImgTeam)
        }
    }
}

