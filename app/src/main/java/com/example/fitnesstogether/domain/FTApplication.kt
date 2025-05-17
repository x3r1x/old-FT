package com.example.fitnesstogether

import android.app.Application
import com.example.fitnesstogether.domain.ApiService

class FTApplication : Application() {
    companion object {
        lateinit var apiService: ApiService
            private set
    }

    override fun onCreate() {
        super.onCreate()
        apiService = RetrofitClient.create(applicationContext)
    }
}