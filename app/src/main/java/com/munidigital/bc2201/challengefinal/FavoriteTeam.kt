package com.munidigital.bc2201.challengefinal

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "favorites_table")
data class FavoriteTeam(@PrimaryKey val idFavoriteTeam:String,
                   val nameFavoriteTeam:String,
                    val imgFavorite:String): Parcelable