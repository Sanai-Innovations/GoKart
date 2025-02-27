package com.sanai.gokart.presentation.fragments.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.sanai.gokart.data.models.Product
import com.sanai.gokart.data.models.response.dashboard.DashboardResponse
import com.sanai.gokart.data.util.Resource
import com.sanai.gokart.databinding.FragmentDashboardBinding
import com.sanai.gokart.presentation.activities.product_detail.ProductDetailActivity
import com.sanai.gokart.presentation.adapter.BannerViewPagerAdapter
import com.sanai.gokart.presentation.adapter.ProductDealsAdapter
import com.sanai.gokart.presentation.adapter.ProductListAdapter
import com.sanai.gokart.presentation.util.Logger
import com.sanai.gokart.presentation.viewmodel.dashboard.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private val binding get() = _binding!!
    private var _binding: FragmentDashboardBinding? = null

    private lateinit var bannerViewPager: ViewPager2
    private lateinit var viewModel: DashboardViewModel
    private lateinit var dealsRecyclerView: RecyclerView
    private lateinit var productsRecyclerView: RecyclerView
    private lateinit var categoryRecyclerView: RecyclerView
    private lateinit var bestSellingRecyclerView: RecyclerView

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

    private fun initializeVariables() {
        dealsRecyclerView = binding.dealsRv
        bannerViewPager = binding.bannerViewPager
        categoryRecyclerView = binding.categoryRv
        bestSellingRecyclerView = binding.bestSellingRv
        productsRecyclerView = binding.popularProductsRv

        // get viewModel
        viewModel = ViewModelProvider(requireActivity())[DashboardViewModel::class.java]
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
                    setBannerAdapter(it.data)
                    setDealsRecyclerViewAdapter(it.data?.deals)
                    setProductsRecyclerViewAdapter(it.data?.newCollection)
                    setBestSellingRecyclerViewAdapter(it.data?.bestSelling)
                }
            }
        }
    }

    private fun setBestSellingRecyclerViewAdapter(bestSelling: List<Product>?) {
        bestSellingAdapter.setList(bestSelling!!)
        binding.bestSellingRv.layoutManager = GridLayoutManager(requireActivity(), 2)
        binding.bestSellingRv.adapter = bestSellingAdapter
        bestSellingAdapter.setOnItemClickListener {
            startActivity(Intent(requireActivity(), ProductDetailActivity::class.java).apply {
                putExtra("productId", it.productId)
            })
        }
    }

    private fun setProductsRecyclerViewAdapter(newCollection: List<Product>?) {
        newCollectionAdapter.setList(newCollection!!)
        binding.popularProductsRv.layoutManager = GridLayoutManager(requireActivity(), 2)
        binding.popularProductsRv.adapter = newCollectionAdapter
    }

    private fun setDealsRecyclerViewAdapter(deals: List<Product>?) {
        dealsAdapter.setList(deals!!)
        binding.dealsRv.layoutManager = GridLayoutManager(requireActivity(), 2)
        binding.dealsRv.adapter = dealsAdapter
    }

    private fun setBannerAdapter(data: DashboardResponse?) {
        bannerViewPagerAdapter.setList(data?.banners!!)
        bannerViewPager.adapter = bannerViewPagerAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}