package com.example.indieartistsapp.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.indieartistsapp.R
import com.example.indieartistsapp.application.IndieArtistApplication
import com.example.indieartistsapp.data.AppRepository
import com.example.indieartistsapp.databinding.ActivityArtistDetailBinding
import com.example.indieartistsapp.utils.Constants.LOGTAG
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.Retrofit

private const val GAME_ID = "game_id"
class ArtistDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArtistDetailBinding
    private lateinit var repository: AppRepository
    private var gameId: Int? = null
    private lateinit var retrofit: Retrofit


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityArtistDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        repository = (application as IndieArtistApplication).repository

        //val intent = intent
        gameId = intent.getIntExtra("id", 404)
        Log.d(LOGTAG, "Id recibido por intent $gameId")


        callAPIDetails(gameId.toString())
    }

    private fun callAPIDetails(gameId: String?){
        lifecycleScope.launch {
            try {
                val artistDetail = repository.getArtistDetails(gameId)
                Log.d(LOGTAG,"Id recibido ${artistDetail}")
            }catch (e: IOException){

            }
        }
    }
}