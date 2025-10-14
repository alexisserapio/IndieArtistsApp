package com.example.indieartistsapp.data

import com.example.indieartistsapp.data.api.IndieArtistsAPI
import com.example.indieartistsapp.data.api.model.ArtistDTO
import com.example.indieartistsapp.data.api.model.ArtistDetailDTO
import retrofit2.Retrofit

class AppRepository(
    private val retrofit: Retrofit
){
    private val indieArtistsAPI = retrofit.create(IndieArtistsAPI::class.java)

    suspend fun getAllArtists(): List<ArtistDTO> = indieArtistsAPI.getAllArtists()

    suspend fun getArtistDetails(id: String?): ArtistDetailDTO = indieArtistsAPI.getArtistDetails(id)
}