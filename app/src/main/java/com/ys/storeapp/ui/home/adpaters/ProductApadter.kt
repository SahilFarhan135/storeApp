package com.ys.storeapp.ui.home.adpaters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ys.storeapp.databinding.RvProductBinding
import com.ys.storeapp.ui.home.model.CategoryModel
import com.ys.storeapp.ui.home.model.ProductItem

class ProductApadter : RecyclerView.Adapter<ProductApadter.ProductViewHolder>() {

    private val items = ArrayList<ProductItem>()


    var onProductMenuClick: OnProductMenuClick? = null

    interface OnProductMenuClick {
        fun onMenuClick(productItem: ProductItem, position: Int)
    }

    fun ProductApadterCallBack(onProductMenuClick: OnProductMenuClick?) {
        this.onProductMenuClick = onProductMenuClick
    }

    fun submitList(productsItemList: List<ProductItem>) {
        items.clear()
        items.addAll(productsItemList)
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(private val binding: RvProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun attachItem(ProductItem: ProductItem) {
            with(binding) {
                price.text = ProductItem.price.toString().trim()
                productImg.setImageResource(ProductItem.img)
                name.text = ProductItem.name.toString().trim()
                imgMenu.setOnClickListener {
                    onProductMenuClick?.onMenuClick(ProductItem,position)
                }

            }
        }

    }

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): ProductViewHolder {
        val binding = RvProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.attachItem(items[position])
    }

    override fun getItemCount(): Int = items.size
}