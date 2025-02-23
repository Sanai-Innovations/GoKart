package com.sanai.gokart.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sanai.gokart.databinding.EducationPagerLayoutBinding

class EducationViewPagerAdapter :
    RecyclerView.Adapter<EducationViewPagerAdapter.EducationViewHolder>() {

    val listItem = arrayListOf(
        "ViewPager2 is a versatile component in Android that allows users to swipe horizontally between different fragments. In this implementation article, we will explore how to create a ViewPager2 with Kotlin and Fragments.",
        "Congratulations! You have successfully implemented a ViewPager2 with Kotlin and Fragments.",
        "This component is a powerful tool for creating interactive and user-friendly interfaces in your Android applications. Feel free to experiment further with different fragments and layouts to suit your app’s needs. Happy coding!",
        "By following these steps, you can now have a customized TabLayout with both icons and text for each tab, making your app’s UI more visually appealing and user-friendly."
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EducationViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = EducationPagerLayoutBinding.inflate(layoutInflater, parent, false)
        return EducationViewHolder(binding)
    }

    override fun getItemCount() = listItem.size

    override fun onBindViewHolder(holder: EducationViewHolder, position: Int) {
        holder.bind(listItem[position])
    }

    inner class EducationViewHolder(binding: EducationPagerLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val pagerText = binding.pagerText

        fun bind(text: String) {
            pagerText.text = text
        }
    }
}