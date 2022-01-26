package com.munidigital.bc2201.challengefinal

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "teams_table")
data class TeamArg(@PrimaryKey val idTeam:String,
                    val nameTeam:String,
                    val nameAlternateTeam:String,
                    val nameLeague:String,
                    val nameAlternateLeague:String?,
                    val nameStadiumLocation:String,
                    val nameStadium:String,
                    val description:String?,
                    val imageUrl:String,
                    val isSelected:Int,
                    val webSite:String,
                    val facebook:String,
                    val twitter:String,
                    val instagram:String): Parcelable
