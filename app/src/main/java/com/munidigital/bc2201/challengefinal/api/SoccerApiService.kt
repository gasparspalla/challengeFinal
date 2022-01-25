package com.munidigital.bc2201.challengefinal.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface SoccerApiService {
    @GET("search_all_teams.php?s=Soccer&c=Argentina")
    suspend fun getAllTeamsArgentina():SoccerJsonResponse
}

private var retrofit = Retrofit.Builder()
    .baseUrl("https://www.thesportsdb.com/api/v1/json/2/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

var service:SoccerApiService = retrofit.create(SoccerApiService::class.java)