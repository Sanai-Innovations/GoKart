package com.sanai.gokart.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.sanai.gokart.data.models.Product
import com.sanai.gokart.databinding.ItemDealProductBinding
import javax.inject.Inject

class ProductListAdapter @Inject constructor() :
    RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>() {

    private var onItemClickListener: ((Product) -> Unit)? = null
    private val diffUtil = object : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.productId == newItem.productId
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
    private val asyncListDiffer = AsyncListDiffer(this, diffUtil)

    fun setList(list: List<Product>) {
        asyncListDiffer.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val binding =
            ItemDealProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return asyncListDiffer.currentList.size
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        val article = asyncListDiffer.currentList[position]
        holder.bind(article)
    }

    fun setOnItemClickListener(listener: (item: Product) -> Unit) {
        onItemClickListener = listener
    }

    inner class ProductListViewHolder(private val binding: ItemDealProductBinding) :
        ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.title.text = product.title
            binding.amount.text = product.price
            Glide.with(binding.productImage.context)
                .load(product.productImages?.get(0))
                .into(binding.productImage)

            binding.root.setOnClickListener {
                onItemClickListener?.invoke(product)
            }
        }
    }
}