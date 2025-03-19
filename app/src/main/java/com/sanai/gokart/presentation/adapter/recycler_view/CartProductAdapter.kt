package com.sanai.gokart.presentation.adapter.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.sanai.gokart.data.models.response.cart.CartProductItem
import com.sanai.gokart.databinding.ItemCartProductBinding
import com.sanai.gokart.presentation.util.Logger
import com.sanai.gokart.presentation.util.loadImage
import javax.inject.Inject

class CartProductAdapter @Inject constructor() :
    RecyclerView.Adapter<CartProductAdapter.CartProductViewHolder>() {

    private var onItemClickListener: ((CartProductItem) -> Unit)? = null
    private val diffUtil = object : DiffUtil.ItemCallback<CartProductItem>() {
        override fun areItemsTheSame(oldItem: CartProductItem, newItem: CartProductItem): Boolean {
            return oldItem.productId == newItem.productId
        }

        override fun areContentsTheSame(
            oldItem: CartProductItem,
            newItem: CartProductItem
        ): Boolean {
            return oldItem == newItem
        }
    }
    private val asyncListDiffer = AsyncListDiffer(this, diffUtil)

    fun setList(list: List<CartProductItem>) {
        Logger.d("CartProductAdapter: setList: $list")
        asyncListDiffer.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartProductViewHolder {
        val binding =
            ItemCartProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        Logger.d("CartProductAdapter: Number of products in cart ${asyncListDiffer.currentList.size}")
        return asyncListDiffer.currentList.size
    }

    override fun onBindViewHolder(holder: CartProductViewHolder, position: Int) {
        val cartItem = asyncListDiffer.currentList[position]
        holder.bind(cartItem)
    }

    fun setOnItemClickListener(listener: (item: CartProductItem) -> Unit) {
        onItemClickListener = listener
    }

    inner class CartProductViewHolder(private val binding: ItemCartProductBinding) :
        ViewHolder(binding.root) {

        fun bind(product: CartProductItem) {
            Logger.d("CartProductAdapter: Creating view for $product")
            binding.title.text = product.title
            binding.subTitle.text = product.title
            binding.price.text = product.finalPrice.toString()
            binding.productImage.loadImage(product.thumbnail)

            binding.root.setOnClickListener {
                onItemClickListener?.invoke(product)
            }
        }
    }
}