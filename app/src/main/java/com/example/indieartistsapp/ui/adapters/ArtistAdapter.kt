package com.example.indieartistsapp.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.indieartistsapp.data.api.model.ArtistDTO

class ArtistAdapter(

    private val artists: List<ArtistDTO>,
    private val onGameClick: (ArtistDTO) -> Unit

): RecyclerView.Adapter<ArtistViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArtistViewHolder = ArtistViewHolder.create(parent, onGameClick)

    override fun onBindViewHolder(
        holder: ArtistViewHolder,
        position: Int
    ) {
        holder.bind(artists[position])
    }

    override fun getItemCount(): Int = artists.size

}