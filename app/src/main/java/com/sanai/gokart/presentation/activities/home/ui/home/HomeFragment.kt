package com.sanai.gokart.presentation.activities.home.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.sanai.gokart.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private val binding get() = _binding!!
    private var _binding: FragmentHomeBinding? = null
    private lateinit var bannerViewPager: ViewPager2
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var productRecyclerView: RecyclerView
    private lateinit var categoryRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        bannerViewPager = binding.bannerViewPager
        productRecyclerView = binding.productRecyclerView
        categoryRecyclerView = binding.categoryRecyclerView
        homeViewModel.text.observe(viewLifecycleOwner) {

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}