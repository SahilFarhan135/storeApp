package com.ys.storeapp.ui.home.adpaters

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.ys.storeapp.R
import com.ys.storeapp.ui.home.model.SliderModel

class sliderAdapter(private val sliderModelList: List<SliderModel>) : PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view =
            LayoutInflater.from(container.context).inflate(R.layout.slider_layout, container, false)
        val bannerContainer: ConstraintLayout = view.findViewById(R.id.banner_container)
        if (Build.VERSION.SDK_INT >= 21) {
            bannerContainer.backgroundTintList = ColorStateList.valueOf(
                Color.parseColor(
                    "#f2ebf1"
                )
            )
        }
        val banner = view.findViewById<ImageView>(R.id.banner_slide)
        Glide.with(container.context).load(sliderModelList[position].img).into(banner)
        container.addView(view, 0)
        return view
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return sliderModelList.size
    }
}