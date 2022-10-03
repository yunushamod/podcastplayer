package com.yunushamod.android.popplay.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.yunushamod.android.popplay.repository.ItunesRepo
import com.yunushamod.android.popplay.services.PodcastResponse
import com.yunushamod.android.popplay.util.DateUtils

class PopViewModel(application: Application) :  AndroidViewModel(application){
    private val repository: ItunesRepo = ItunesRepo.getInstance()
    suspend fun searchPodcasts(term: String): List<PodcastSummaryViewData>{
        val results = repository.searchByTerm(term)
        if(results.isSuccessful){
            val podcasts = results.body()?.results
            if(!podcasts.isNullOrEmpty()){
                return podcasts.map {
                    itunesPodcastToPodcastSummaryView(it)
                }
            }
        }
        return emptyList()
    }

    private fun itunesPodcastToPodcastSummaryView(
        itunesPodcast: PodcastResponse.ItunesPodcast
    ): PodcastSummaryViewData{
        return PodcastSummaryViewData(
            itunesPodcast.collectionCensoredName,
            DateUtils.jsonDateToShortDate(itunesPodcast.releaseDate),
            itunesPodcast.artworkUrl30,
            itunesPodcast.feedUrl
        )
    }

    data class PodcastSummaryViewData(
        var name: String? = "",
        var lastUpdated: String? = "",
        var imageUrl: String? = "",
        var feedUrl: String? = ""
    )
}