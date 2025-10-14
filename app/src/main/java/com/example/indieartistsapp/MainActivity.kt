package com.example.indieartistsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.indieartistsapp.data.AppRepository
import com.example.indieartistsapp.data.api.RetrofitHelper
import com.example.indieartistsapp.databinding.ActivityMainBinding
import com.example.indieartistsapp.utils.Constants
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var repository: AppRepository
    private lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        retrofit = RetrofitHelper().getRetrofit()
        repository = AppRepository(retrofit)

        lifecycleScope.launch {
            try {
                val artists = repository.getAllArtists()
                Log.d(Constants.LOGTAG, "Respuesta recibida: ${artists.toString()}")
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}