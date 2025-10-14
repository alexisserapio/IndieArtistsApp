package com.example.indieartistsapp.data.api.model

import com.google.gson.annotations.SerializedName

data class ArtistDTO(//DTO stands for 'Data Transfer Object'
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("artistic_name")
    var artistic_name: String? = null,
    @SerializedName("famous_song")
    var famous_song: String? = null,
    @SerializedName("famous_song_album")
    var famous_song_album: String? = null,
    @SerializedName("image_url")
    var image_url: String? = null

)
