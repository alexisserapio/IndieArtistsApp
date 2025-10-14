package com.example.indieartistsapp.data.api.model

import com.google.gson.annotations.SerializedName

data class ArtistDetailDTO(
    @SerializedName("artistic_name")
    var artistic_name: String? = null,
    @SerializedName("real_name")
    var real_name: String? = null,
    @SerializedName("bday_date")
    var bday_date: String? = null,
    @SerializedName("famous_song")
    var famous_song: String? = null,
    @SerializedName("famous_song_album")
    var famous_song_album: String? = null,
    @SerializedName("albums")
    var albums: List<String>? = null,
    @SerializedName("latest_album")
    var latest_album: String? = null,
    @SerializedName("image_url")
    var image_url: String? = null
)
