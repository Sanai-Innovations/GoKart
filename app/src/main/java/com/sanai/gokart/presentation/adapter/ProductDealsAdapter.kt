package com.sanai.gokart.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.sanai.gokart.data.models.Product
import com.sanai.gokart.databinding.ItemDealProductBinding
import com.sanai.gokart.presentation.util.loadImage
import javax.inject.Inject

class ProductDealsAdapter @Inject constructor() :
    RecyclerView.Adapter<ProductDealsAdapter.ProductDealsViewHolder>() {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductDealsViewHolder {
        val binding =
            ItemDealProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductDealsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return asyncListDiffer.currentList.size
    }

    override fun onBindViewHolder(holder: ProductDealsViewHolder, position: Int) {
        val article = asyncListDiffer.currentList[position]
        holder.bind(article)
    }

    fun setOnItemClickListener(listener: (item: Product) -> Unit) {
        onItemClickListener = listener
    }

    inner class ProductDealsViewHolder(private val binding: ItemDealProductBinding) :
        ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.title.text = product.title
            binding.amount.text = product.price
            binding.productImage.loadImage(product.productImages?.get(0))

            binding.root.setOnClickListener {
                onItemClickListener?.invoke(product)
            }
        }
    }
}