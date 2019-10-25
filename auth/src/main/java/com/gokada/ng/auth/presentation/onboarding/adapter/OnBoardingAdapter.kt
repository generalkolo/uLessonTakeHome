package com.gokada.ng.auth.presentation.onboarding.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.gokada.ng.auth.R

/**
 * Created by edetebenezer on 2019-07-30
 **/

class OnBoardingAdapter(private val context: Context) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val layout = inflater.inflate(R.layout.item_onboarding, container, false) as ViewGroup

        val sliderDescription = layout.findViewById(R.id.slider_summary_text) as TextView
        val sliderImage = layout.findViewById(R.id.slider_image) as ImageView

        sliderDescription.text = slideDescriptions[position]
        sliderImage.setImageResource(slideImages[position])

        container.addView(layout)

        return layout
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount() = slideDescriptions.size

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    private companion object {
        val slideDescriptions = listOf(
            "Slider One is the first slider",
            "Slide two happens to be the second slider",
            "Slider three is the final slider"
        )

        val slideImages = listOf(
            R.drawable.ic_dummy_picture,
            R.drawable.ic_dummy_picture,
            R.drawable.ic_dummy_picture
        )
    }
}