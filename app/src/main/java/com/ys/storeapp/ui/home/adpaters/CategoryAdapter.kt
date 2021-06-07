package com.ys.storeapp.ui.home.adpaters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ys.storeapp.R
import com.ys.storeapp.ui.home.model.CategoryModel
import com.ys.storeapp.ui.product.ProductsActivity

class CategoryAdapter(private val categoryModelList: List<CategoryModel>, var context: Context) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_store, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.categoryName.text = categoryModelList[position].shopName
        holder.categoryIcon.setImageResource(categoryModelList[position].icon)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ProductsActivity::class.java)
            intent.putExtra("name", categoryModelList[position].shopName)
            context.startActivity(intent)
        }
    }

    @ColorInt
    fun lightColor(@ColorInt color: Int): Int {
        val hsv = FloatArray(3)
        Color.colorToHSV(color, hsv)
        hsv[2] *= 12.9f
        return Color.HSVToColor(hsv)
    }

    override fun getItemCount(): Int {
        return categoryModelList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryIcon: ImageView
        val categoryName: TextView
        var root: View? = null
        var cardView: CardView
        private fun setCategoryIcon() {}
        private fun setCategoryName(name: String) {
            categoryName.text = name
        }

        init {
            categoryIcon = itemView.findViewById(R.id.img_category_icon)
            categoryName = itemView.findViewById(R.id.tv_category_name)
            cardView = itemView.findViewById(R.id.categoryCard)
            itemView.setOnClickListener { }
        }
    }
}