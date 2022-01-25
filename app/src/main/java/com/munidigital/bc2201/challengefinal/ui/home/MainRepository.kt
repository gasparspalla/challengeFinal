package com.munidigital.bc2201.challengefinal.ui.home

import android.util.Log
import com.munidigital.bc2201.challengefinal.TeamArg
import com.munidigital.bc2201.challengefinal.api.SoccerJsonResponse
import com.munidigital.bc2201.challengefinal.api.service
import com.munidigital.bc2201.challengefinal.database.SoccerDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository(private val dataBase: SoccerDataBase) {

    val teamList=dataBase.soccerDAO.getTeams()

    suspend fun fetchTeams() {
        return withContext(Dispatchers.IO){
            val temsJsonResponse =service.getAllTeamsArgentina()
            val teams = parseEqResult(temsJsonResponse)
            dataBase.soccerDAO.insertAll(teams)
        }


    }
    private fun parseEqResult(soccerJsonResponse: SoccerJsonResponse):MutableList<TeamArg> {
        val teamsList= mutableListOf<TeamArg>()

        val JsonteamList=soccerJsonResponse.teams

        for (team in JsonteamList){
            val idTeam=team.idTeam
            val nameTeam=team.strTeam
            val nameAlternateTeam=team.strAlternate
            val nameLeague=team.strLeague
            val nameAlternateLeague=team.strLeague2
            val nameStadiumLocation=team.strStadiumLocation
            val nameStadium=team.strStadium
            val description=team.strDescriptionEN
            val image=team.strTeamBadge

            teamsList.add(TeamArg(idTeam,nameTeam,nameAlternateTeam,nameLeague,nameAlternateLeague,nameStadiumLocation,nameStadium,description,image))
        }
        return teamsList
    }
}