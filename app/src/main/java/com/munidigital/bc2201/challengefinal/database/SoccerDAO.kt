package com.munidigital.bc2201.challengefinal.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.munidigital.bc2201.challengefinal.TeamArg

@Dao
interface SoccerDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(teams:MutableList<TeamArg>)

    @Query("SELECT * FROM teams_table")
    fun getTeams(): LiveData<MutableList<TeamArg>>
}