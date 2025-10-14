package com.example.indieartistsapp.data.api

import com.example.indieartistsapp.data.api.model.ArtistDTO
import com.example.indieartistsapp.data.api.model.ArtistDetailDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface IndieArtistsAPI {

    @GET("all_artists")
    suspend fun getAllArtists(): List<ArtistDTO>

    @GET("artist/{id}")
    suspend fun getArtistDetails(
        @Path("id") id: String?
    ): ArtistDetailDTO

}