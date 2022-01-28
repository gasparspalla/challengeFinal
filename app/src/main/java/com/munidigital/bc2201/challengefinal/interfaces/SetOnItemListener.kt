package com.munidigital.bc2201.challengefinal.interfaces

import com.munidigital.bc2201.challengefinal.FavoriteTeam
import com.munidigital.bc2201.challengefinal.TeamArg

interface SetOnItemListener {
    fun onTeamSelected(team: TeamArg)

    fun onFavoriteSelected(favorite: FavoriteTeam)
}