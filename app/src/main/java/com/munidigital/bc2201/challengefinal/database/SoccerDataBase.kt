package com.munidigital.bc2201.challengefinal.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.munidigital.bc2201.challengefinal.FavoriteTeam
import com.munidigital.bc2201.challengefinal.TeamArg

@Database(entities = [TeamArg::class, FavoriteTeam::class], version = 1)
abstract class SoccerDataBase :RoomDatabase(){

    abstract val soccerDAO:SoccerDAO

}
private lateinit var INSTANCE:SoccerDataBase

fun getDataBase(context: Context):SoccerDataBase{
    synchronized(SoccerDataBase::class.java){
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                SoccerDataBase::class.java,
                "team_DB"
            ).build()
        }
        return INSTANCE
    }
}