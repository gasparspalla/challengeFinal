package com.munidigital.bc2201.challengefinal.ui.home


import androidx.lifecycle.viewModelScope
import com.munidigital.bc2201.challengefinal.FavoriteTeam
import com.munidigital.bc2201.challengefinal.TeamArg
import com.munidigital.bc2201.challengefinal.api.SoccerJsonResponse
import com.munidigital.bc2201.challengefinal.api.service
import com.munidigital.bc2201.challengefinal.database.SoccerDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainRepository(private val dataBase: SoccerDataBase) {

    val teamList=dataBase.soccerDAO.getTeams()

    val favoriteList=dataBase.soccerDAO.getFavorite()

    suspend fun fetchTeams() {
        return withContext(Dispatchers.IO){
            val temsJsonResponse =service.getAllTeamsArgentina()
            val countRowTeam=dataBase.soccerDAO.teamRowCount()
            if (countRowTeam==0){
                val teams = parseEqResult(temsJsonResponse)
                dataBase.soccerDAO.insertAll(teams)
            }
        }
    }



    suspend fun setFavoriteData(itemFavorite:FavoriteTeam){
        return withContext(Dispatchers.IO){
            val favoriteList=dataBase.soccerDAO.isFavorite(itemFavorite.idFavoriteTeam)
            if (favoriteList==0){
                dataBase.soccerDAO.insertItemFavorite(itemFavorite)
                dataBase.soccerDAO.activeFavoriteImg(itemFavorite.idFavoriteTeam)

            }
            else{
                dataBase.soccerDAO.delete(itemFavorite)
                dataBase.soccerDAO.desactiveFavoriteImg(itemFavorite.idFavoriteTeam)

            }
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
            val website=team.strWebsite

            val facebook=team.strFacebook
            val twitter=team.strTwitter
            val instagram=team.strInstagram

            teamsList.add(TeamArg(idTeam,nameTeam,nameAlternateTeam,nameLeague,nameAlternateLeague,nameStadiumLocation,nameStadium,description,image,0,website,facebook,twitter,instagram))
        }
        return teamsList
    }




}