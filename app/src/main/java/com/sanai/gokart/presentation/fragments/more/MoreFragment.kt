package com.sanai.gokart.presentation.fragments.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.sanai.gokart.databinding.FragmentHomeBinding
import com.sanai.gokart.databinding.FragmentMoreBinding

class MoreFragment : Fragment() {
    private val binding get() = _binding!!
    private var _binding: FragmentMoreBinding? = null
    private lateinit var bannerViewPager: ViewPager2
    private lateinit var moreViewModel: MoreViewModel
    private lateinit var productRecyclerView: RecyclerView
    private lateinit var categoryRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        moreViewModel = ViewModelProvider(this)[MoreViewModel::class.java]
        _binding = FragmentMoreBinding.inflate(inflater, container, false)
        val root: View = binding.root

        moreViewModel.text.observe(viewLifecycleOwner) {

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}