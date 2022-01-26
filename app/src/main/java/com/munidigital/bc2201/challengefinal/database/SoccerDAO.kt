package com.munidigital.bc2201.challengefinal.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.munidigital.bc2201.challengefinal.FavoriteTeam
import com.munidigital.bc2201.challengefinal.TeamArg

@Dao
interface SoccerDAO {
    //tabla TEAMS
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(teams: MutableList<TeamArg>)

//    @Query("SELECT E.idTeam FROM TEAMS_TABLE E WHERE E.idTeam=:id")
//    fun getIndentic(id:String): LiveData<MutableList<TeamArg>>

//    @Query("UPDATE TEAMS_TABLE SET isSelected=1 WHERE idTeam=:idFavorite")
//    fun isSelected(idFavorite:String)

    @Query("SELECT * FROM teams_table")
    fun getTeams(): LiveData<MutableList<TeamArg>>

    //tabla FAVORITOS
    @Query("SELECT * FROM favorites_table")
    fun getFavorite(): LiveData<MutableList<FavoriteTeam>>

    @Insert
    fun insertItemFavorite(favoriteItem:FavoriteTeam)


    @Query("SELECT COUNT() FROM favorites_table WHERE idFavoriteTeam=:id")
    fun isFavorite(id:String):Int

    @Delete
    fun delete(favoriteItem:FavoriteTeam)


}
