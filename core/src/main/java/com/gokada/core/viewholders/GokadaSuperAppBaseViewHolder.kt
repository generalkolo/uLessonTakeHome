package com.gokada.core.viewholders

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class GokadaSuperAppBaseViewHolder <T> (
    binding: ViewDataBinding
): RecyclerView.ViewHolder(binding.root) {

    abstract fun bindItem(item: T)
}