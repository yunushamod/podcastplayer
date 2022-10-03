package com.yunushamod.android.popplay

import android.app.Application
import com.yunushamod.android.popplay.repository.ItunesRepo
import com.yunushamod.android.popplay.services.ItunesService

class PopPlayApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val itunesService = ItunesService.instance
        ItunesRepo.initialize(itunesService)
    }
}