package com.yunushamod.android.popplay.repository

import com.yunushamod.android.popplay.services.ItunesService

class ItunesRepo private constructor(private val itunesService: ItunesService) {
    suspend fun searchByTerm(term: String) = itunesService.searchPodcastByTerm(term)
    companion object{
        private var INSTANCE : ItunesRepo? = null
        fun initialize(itunesService: ItunesService){
            if(INSTANCE == null) {
                INSTANCE = ItunesRepo(itunesService)
            }
        }
        fun getInstance() = INSTANCE ?: throw IllegalStateException("ItunesRepo cannot be null. Initialize")
    }
}