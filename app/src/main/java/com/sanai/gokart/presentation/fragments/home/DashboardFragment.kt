package com.sanai.gokart.presentation.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.sanai.gokart.data.util.Resource
import com.sanai.gokart.databinding.FragmentDashboardBinding
import com.sanai.gokart.presentation.adapter.BannerViewPagerAdapter
import com.sanai.gokart.presentation.adapter.ProductDealsAdapter
import com.sanai.gokart.presentation.adapter.ProductListAdapter
import com.sanai.gokart.presentation.util.logging.Logger
import com.sanai.gokart.presentation.viewmodel.dashboard.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private val binding get() = _binding!!
    private var _binding: FragmentDashboardBinding? = null

    private lateinit var bannerViewPager: ViewPager2
    private lateinit var viewModel: DashboardViewModel
    private lateinit var productRecyclerView: RecyclerView
    private lateinit var categoryRecyclerView: RecyclerView

    @Inject
    lateinit var dealsAdapter: ProductDealsAdapter

    @Inject
    lateinit var bestSellingAdapter: ProductListAdapter

    @Inject
    lateinit var newCollectionAdapter: ProductListAdapter

    @Inject
    lateinit var bannerViewPagerAdapter: BannerViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        initializeVariables()
        bindObservers()
        return binding.root
    }

    private fun bindObservers() {
        viewModel.dashboardResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    Logger.e("DashboardFragment: $it.message.toString()")
                }

                is Resource.Loading -> {
                    Logger.e("DashboardFragment: Loading")
                }

                is Resource.Success -> {
                    Logger.e("DashboardFragment: Success ${it.data}")
                    bannerViewPagerAdapter.setList(it.data?.banners!!)
                    bannerViewPager.adapter = bannerViewPagerAdapter
                }
            }
        }
    }

    private fun initializeVariables() {
        bannerViewPager = binding.bannerViewPager
        productRecyclerView = binding.productRecyclerView
        categoryRecyclerView = binding.categoryRecyclerView

        // get viewModel
        viewModel = ViewModelProvider(requireActivity())[DashboardViewModel::class.java]
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}