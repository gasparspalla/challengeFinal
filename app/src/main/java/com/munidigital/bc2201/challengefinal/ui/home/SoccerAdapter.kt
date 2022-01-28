package com.munidigital.bc2201.challengefinal.ui.home

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.munidigital.bc2201.challengefinal.R
import com.munidigital.bc2201.challengefinal.TeamArg
import com.munidigital.bc2201.challengefinal.databinding.TeamListItemBinding
import java.util.*
import java.util.stream.Collectors

class SoccerAdapter(private val activity: Activity):
    ListAdapter<TeamArg, SoccerAdapter.SoccerViewHolder>(DiffCallback) {

    private lateinit var listTeam:MutableList<TeamArg>
    private val listOriginal= mutableListOf<TeamArg>()

    fun completeList(teams:MutableList<TeamArg>){
        listTeam=teams
        listOriginal.addAll(listTeam)


    }

    companion object DiffCallback : DiffUtil.ItemCallback<TeamArg>() {
        override fun areItemsTheSame(oldItem: TeamArg, newItem: TeamArg): Boolean {
            return oldItem.idTeam == newItem.idTeam
        }

        override fun areContentsTheSame(oldItem: TeamArg, newItem: TeamArg): Boolean {
            return oldItem == newItem
        }

    }

    lateinit var onItemClickListener: (TeamArg) -> Unit
    lateinit var onItemFavoriteClickListener: (TeamArg) -> Unit


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoccerViewHolder {
        val binding = TeamListItemBinding.inflate(LayoutInflater.from(parent.context))
        return SoccerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SoccerViewHolder, position: Int) {
        val teams: TeamArg = getItem(position)
        holder.bind(teams)
    }


    @SuppressLint("NotifyDataSetChanged")
    @RequiresApi(Build.VERSION_CODES.N)
    fun filter(txtBuscar:String){
        if (txtBuscar.isBlank()){
            listTeam.clear()
            listTeam.addAll(listOriginal)
        }
        else{
            val collection=listTeam.stream().filter { i ->
                i.nameTeam.lowercase(Locale.getDefault()).contains(txtBuscar.lowercase(Locale.getDefault()))
            }.collect(Collectors.toList())
            listTeam.clear()
            listTeam.addAll(collection)
        }
        notifyDataSetChanged()
    }


    inner class SoccerViewHolder(private val binding: TeamListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(teams: TeamArg) {

            if (teams.isSelected==0)binding.imgFavorite.setImageResource(R.drawable.ic_favorite_border)
            else binding.imgFavorite.setImageResource(R.drawable.ic_favorite_backfill)



            binding.tvNameShortTeam.text = teams.nameAlternateTeam
            binding.tvNameLongTeam.text = teams.nameTeam

            val img = binding.imgTeam
            Glide.with(activity).load(teams.imageUrl).into(img)
            binding.root.setOnClickListener {
                if (::onItemClickListener.isInitialized) {
                    onItemClickListener(teams)
                }
            }
            binding.imgFavorite.setOnClickListener {
                if (::onItemFavoriteClickListener.isInitialized) {
                    onItemFavoriteClickListener(teams)
                }
            }
        }


    }
}
