package com.ulesson.takehome.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ulesson.core.listeners.BindableItemClickListener
import com.ulesson.core.utils.AutoUpdateRecyclerView
import com.ulesson.core.viewholders.AppBaseViewHolder
import com.ulesson.takehome.presentation.MainActivityViewModel
import kotlin.properties.Delegates

abstract class SingleLayoutSubjectsRecAdapter<B : ViewDataBinding, D>(
    context: Context,
    @LayoutRes private val layoutId: Int,
    private val itemClickListener: BindableItemClickListener<D>?,
    private val viewModel: MainActivityViewModel
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), AutoUpdateRecyclerView {

    abstract fun getItemBindingVariable(): Int

    abstract fun getItemClickListenerBindingVariable(): Int

    abstract fun getMainViewModelBindingVariable(): Int

    var items: List<D> by Delegates.observable(emptyList()) { _, oldItems, newItems ->
        autoNotify(oldItems, newItems) { oldItem, newItem ->
            oldItem.hashCode() == newItem.hashCode()
        }
    }

    fun addNewItems(items: List<D>) {
        this.items = this.items.toMutableList().apply {
            addAll(items)
        }
    }

    fun removeItem(item: D) {
        this.items = this.items.toMutableList().apply {
            remove(item)
        }
    }

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: B = DataBindingUtil.inflate(inflater, layoutId, parent, false)
        return SingleLayoutViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as AppBaseViewHolder<D>).bindItem(items[position])

    inner class SingleLayoutViewHolder(
        private val binding: B
    ) : AppBaseViewHolder<D>(binding) {

        override fun bindItem(item: D) {
            binding.apply {
                setVariable(getItemBindingVariable(), item)
                setVariable(getItemClickListenerBindingVariable(), itemClickListener)
                setVariable(getMainViewModelBindingVariable(), viewModel)
                executePendingBindings()
            }
        }
    }
}
