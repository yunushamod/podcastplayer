package com.yunushamod.android.popplay.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yunushamod.android.popplay.databinding.SearchItemBinding
import com.yunushamod.android.popplay.viewmodels.PopViewModel

class PodcastListAdapter(private var podcastSummaryViewList: List<PopViewModel.PodcastSummaryViewData>?,
private val podcastListAdapterListener:  PodcastListAdapterListener,
private val parentActivity: Activity
) : RecyclerView.Adapter<PodcastListAdapter.ViewHolder>(){
    interface PodcastListAdapterListener{
        fun onShowDetails(podcastSummaryViewData: PopViewModel.PodcastSummaryViewData)
    }

    inner class ViewHolder(databinding: SearchItemBinding, private val podcastListAdapterListener: PodcastListAdapterListener)
        : RecyclerView.ViewHolder(databinding.root){
            var podcastSummaryView: PopViewModel.PodcastSummaryViewData? = null
        val nameTextView = databinding.podcastNameTextView
        val lastUpdatedTextView = databinding.podcastLastUpdatedView
        val podcastImageView: ImageView = databinding.podcastImage
        init {
            databinding.searchItem.setOnClickListener{
                podcastSummaryView?.let {
                    podcastListAdapterListener.onShowDetails(it)
                }
            }
        }
        }
    fun setSearchData(podcastSummaryViewData: List<PopViewModel.PodcastSummaryViewData>){
        podcastSummaryViewList = podcastSummaryViewData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        podcastListAdapterListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val searchViewList = podcastSummaryViewList
        searchViewList?.let {
            val searchView = it[position]
            holder.podcastSummaryView = searchView
            holder.nameTextView.text = searchView.name
            holder.lastUpdatedTextView.text = searchView.lastUpdated
            // Use Glide to load image
            Glide.with(parentActivity).load(searchView.imageUrl)
                .into(holder.podcastImageView)
        }
    }

    override fun getItemCount(): Int = podcastSummaryViewList?.size ?: 0
}