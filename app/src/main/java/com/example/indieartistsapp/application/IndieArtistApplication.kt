package com.example.indieartistsapp.application

import android.app.Application
import com.example.indieartistsapp.data.AppRepository
import com.example.indieartistsapp.data.api.RetrofitHelper

class IndieArtistApplication: Application() {

    private val retrofit by lazy{
        RetrofitHelper().getRetrofit()
    }

    val repository by lazy{
        AppRepository(retrofit)
    }

}