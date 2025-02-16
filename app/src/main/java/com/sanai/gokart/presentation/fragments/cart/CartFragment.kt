package com.sanai.gokart.presentation.fragments.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.sanai.gokart.databinding.FragmentCartBinding
import com.sanai.gokart.databinding.FragmentHomeBinding

class CartFragment : Fragment() {
    private val binding get() = _binding!!
    private var _binding: FragmentCartBinding? = null
    private lateinit var bannerViewPager: ViewPager2
    private lateinit var cartViewModel: CartViewModel
    private lateinit var productRecyclerView: RecyclerView
    private lateinit var categoryRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        cartViewModel = ViewModelProvider(this)[CartViewModel::class.java]
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        val root: View = binding.root

        bannerViewPager = binding.bannerViewPager
        productRecyclerView = binding.productRecyclerView
        categoryRecyclerView = binding.categoryRecyclerView
        cartViewModel.text.observe(viewLifecycleOwner) {

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}