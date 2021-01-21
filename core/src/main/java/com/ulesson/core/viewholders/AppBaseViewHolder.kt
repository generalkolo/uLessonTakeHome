package com.ulesson.core.viewholders

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class AppBaseViewHolder <T> (
    binding: ViewDataBinding
): RecyclerView.ViewHolder(binding.root) {

    abstract fun bindItem(item: T)
}
