package com.sanai.gokart.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sanai.gokart.data.models.response.dashboard.Banner
import com.sanai.gokart.databinding.BannerPagerLayoutBinding
import javax.inject.Inject

class BannerViewPagerAdapter @Inject constructor() :
    RecyclerView.Adapter<BannerViewPagerAdapter.BannerViewHolder>() {

    private val banners = mutableListOf<Banner>()

    fun setList(list: List<Banner>) {
        banners.clear()
        banners.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = BannerPagerLayoutBinding.inflate(layoutInflater, parent, false)
        return BannerViewHolder(binding)
    }

    override fun getItemCount() = banners.size

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.bind(banners[position])
    }

    inner class BannerViewHolder(binding: BannerPagerLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val bannerImageView = binding.bannerImage

        fun bind(banner: Banner) {
            Glide.with(bannerImageView.context)
                .load(banner.url)
                .into(bannerImageView)
        }
    }
}