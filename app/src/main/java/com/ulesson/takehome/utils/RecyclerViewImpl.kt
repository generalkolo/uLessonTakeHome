package com.ulesson.takehome.utils

import android.content.Context
import com.ulesson.core.adapters.SingleLayoutRecyclerAdapter
import com.ulesson.core.listeners.BindableItemClickListener
import com.ulesson.domain.models.LessonsItem
import com.ulesson.takehome.BR
import com.ulesson.takehome.R
import com.ulesson.takehome.databinding.LessonsItemBinding
import com.ulesson.takehome.presentation.MainActivityViewModel

class LessonItemAdapterImpl(context: Context, viewModel: MainActivityViewModel) :
    SingleLayoutRecyclerAdapter<LessonsItemBinding, LessonsItem>(
        context = context,
        itemClickListener = object : BindableItemClickListener<LessonsItem> {
            override fun onItemClicked(item: LessonsItem) {
                viewModel.setClickedLesson(item)
            }
        },
        layoutId = R.layout.lessons_item
    ) {
    override fun getItemBindingVariable(): Int = BR.lesson

    override fun getItemClickListenerBindingVariable(): Int = BR.Listener
}
