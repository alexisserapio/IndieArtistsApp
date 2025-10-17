package com.example.indieartistsapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.indieartistsapp.data.api.model.ArtistDTO
import com.example.indieartistsapp.databinding.ArtistElementBinding

class ArtistViewHolder(
    private val binding: ArtistElementBinding,
    private val onGameClick: (ArtistDTO) -> Unit
): RecyclerView.ViewHolder(binding.root) {

    private var currentItem: ArtistDTO? = null

    init {
        binding.root.setOnClickListener {
            currentItem?.let(onGameClick)
        }
    }

    fun bind(artist: ArtistDTO){
        currentItem = artist

        binding.tvTitle.text = artist.artistic_name
        binding.tvFamousSong.text = artist.famous_song
        binding.tvSongAlbum.text = artist.famous_song_album

        Glide.with(binding.root.context)
            .load(artist.image_url)
            .into(binding.ivArtistImage)
    }

    companion object{
        fun create(
            parent: ViewGroup,
            onGameClick: (ArtistDTO) -> Unit
        ): ArtistViewHolder{
            val binding = ArtistElementBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ArtistViewHolder(binding, onGameClick)
        }
    }

}