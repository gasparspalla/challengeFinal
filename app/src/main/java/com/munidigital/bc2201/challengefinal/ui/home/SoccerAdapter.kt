package com.munidigital.bc2201.challengefinal.ui.home

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.munidigital.bc2201.challengefinal.TeamArg
import com.munidigital.bc2201.challengefinal.databinding.TeamListItemBinding

class SoccerAdapter(private val activity: Activity):
    ListAdapter<TeamArg, SoccerAdapter.SoccerViewHolder>(DiffCallback){

    companion object DiffCallback: DiffUtil.ItemCallback<TeamArg>(){
        override fun areItemsTheSame(oldItem: TeamArg, newItem: TeamArg): Boolean {
            return oldItem.idTeam==newItem.idTeam
        }

        override fun areContentsTheSame(oldItem: TeamArg, newItem: TeamArg): Boolean {
            return oldItem==newItem
        }

    }

    lateinit var onItemClickListener: (TeamArg) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoccerViewHolder {
        val binding=TeamListItemBinding.inflate(LayoutInflater.from(parent.context))
        return SoccerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SoccerViewHolder, position: Int) {
        val teams: TeamArg =getItem(position)
        holder.bind(teams)
    }


    inner class SoccerViewHolder(private val binding: TeamListItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(teams: TeamArg){
            binding.tvNameTeam.text=teams.nameTeam
            binding.tvAlternateTeam.text=teams.nameAlternateTeam
            val img=binding.imgTeam
            Glide.with(activity).load(teams.imageUrl).into(img)
        }
    }
}