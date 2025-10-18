package com.example.indieartistsapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.indieartistsapp.R
import com.example.indieartistsapp.application.IndieArtistApplication
import com.example.indieartistsapp.data.AppRepository
import com.example.indieartistsapp.databinding.ActivityMainBinding
import com.example.indieartistsapp.ui.adapters.ArtistAdapter
import com.example.indieartistsapp.utils.Constants
import com.example.indieartistsapp.utils.Constants.LOGTAG
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var repository: AppRepository
    private lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        repository = (application as IndieArtistApplication).repository

        callAPI()
    }

    private fun callAPI(){

        lifecycleScope.launch {
            try {

                binding.pbLoading.visibility = View.VISIBLE
                binding.ivNoWifi.visibility = View.INVISIBLE
                binding.tvNoConnection.visibility = View.INVISIBLE
                binding.buttonRetryConn.visibility = View.INVISIBLE

                val artists = repository.getAllArtists()
                Log.d(Constants.LOGTAG, "Respuesta recibida: ${artists.toString()}")

                binding.rvArtists.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter = ArtistAdapter(artists){ selectedArtist ->

                        selectedArtist.id?.let { id ->

                            val segueToDetailsActivity = Intent(this@MainActivity, ArtistDetailActivity::class.java)
                            Log.d(LOGTAG, "el id mandado es: $id")
                            segueToDetailsActivity.putExtra("id", id)

                            startActivity(segueToDetailsActivity)

                        }

                    }
                }

            }catch (e: IOException ){

                e.printStackTrace()
                binding.pbLoading.visibility = View.INVISIBLE
                binding.ivNoWifi.visibility = View.VISIBLE
                binding.tvNoConnection.visibility = View.VISIBLE
                binding.buttonRetryConn.visibility = View.VISIBLE

                binding.buttonRetryConn.setOnClickListener {
                    callAPI()
                }

            }finally {

                binding.pbLoading.visibility = View.INVISIBLE

            }
        }
    }
}