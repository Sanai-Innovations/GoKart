package com.sanai.gokart.presentation.fragments.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sanai.gokart.data.util.Resource
import com.sanai.gokart.databinding.FragmentCartBinding
import com.sanai.gokart.presentation.adapter.recycler_view.CartProductAdapter
import com.sanai.gokart.presentation.util.Logger

class CartFragment : Fragment() {
    private val binding get() = _binding!!
    private var _binding: FragmentCartBinding? = null

    private lateinit var viewModel: CartViewModel
    private lateinit var adapter: CartProductAdapter
    private lateinit var cartRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        initializeVariables()
        bindObservers()
        return binding.root
    }

    private fun initializeVariables() {
        cartRecyclerView = binding.cartRecyclerView
        adapter = CartProductAdapter()
        cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        cartRecyclerView.setHasFixedSize(true)
        cartRecyclerView.adapter = adapter

        // get viewModel
        viewModel = ViewModelProvider(requireActivity())[CartViewModel::class.java]
        viewModel.getCartItems()
    }

    private fun bindObservers() {
        viewModel.cartItems.observe(viewLifecycleOwner) { resourceData ->
            when (resourceData) {
                is Resource.Error -> {
                    Logger.e("CartFragment: ${resourceData.message.toString()}")
                }

                is Resource.Loading -> {
                    Logger.e("CartFragment: Loading")
                }

                is Resource.Success -> {
                    Logger.e("CartFragment: Success ${resourceData.data}")
                    resourceData.data.let {
                        adapter.setList(it!!)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}