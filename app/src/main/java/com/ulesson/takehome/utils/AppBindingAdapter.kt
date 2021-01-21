package com.ulesson.takehome.utils

import android.graphics.Color
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ulesson.domain.models.LessonsItem
import com.ulesson.takehome.presentation.MainActivityViewModel
import kotlin.random.Random

@BindingAdapter("setCardViewColor")
fun CardView.setCardViewColor(logoStamp: String) {
    val rnd = Random
    val color: Int = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    this.setCardBackgroundColor(color)
}

@BindingAdapter(value = ["imageUrl"], requireAll = true)
fun loadPictureUrl(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        if (it.isNotEmpty()) {
            Glide.with(imageView.context).load(imageUrl).centerInside().into(imageView);
        }
    }
}

@BindingAdapter(value = ["setLessonData", "viewModelParam"], requireAll = true)
fun RecyclerView.setLessonData(lessonItems: List<LessonsItem>?, viewModel: MainActivityViewModel) {
    lessonItems?.let {
        val overviewAdapter = LessonItemAdapterImpl(this.context, viewModel)
        this.adapter = overviewAdapter
        overviewAdapter.items = it
    }
}
