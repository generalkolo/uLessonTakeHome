package com.gokada.core.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.gokada.core.listeners.BindableItemClickListener
import com.gokada.core.utils.AutoUpdateRecyclerView
import com.gokada.core.viewholders.GokadaSuperAppBaseViewHolder
import com.gokada.domain.models.sampleModels.Reasons
import kotlin.properties.Delegates

abstract class SingleLayoutSingleItemRecyclerAdapter <B: ViewDataBinding, D> (
    context: Context,
    @LayoutRes private val layoutId: Int,
    private val itemClickListener: BindableItemClickListener<D>
): RecyclerView.Adapter<RecyclerView.ViewHolder>(), AutoUpdateRecyclerView {

    var selectedItemId: String = "0"
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    abstract fun getItemBindingVariable(): Int

    abstract fun getItemClickListenerBindingVariable(): Int

    abstract fun getRadioButtonId(): Int

    abstract fun isSelectedItem(item: D): Boolean

    var items: List<D> by Delegates.observable(emptyList()) { _, oldItems, newItems ->
        autoNotify(oldItems, newItems) { oldItem, newItem ->
            oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: B = DataBindingUtil.inflate(inflater, layoutId, parent, false)
        return SingleLayoutViewHolder(binding)
    }

    override fun getItemCount(): Int  = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = (holder as GokadaSuperAppBaseViewHolder<D>).bindItem(items[position])

    inner class SingleLayoutViewHolder (
        private val binding: B
    ): GokadaSuperAppBaseViewHolder<D>(binding) {

        override fun bindItem(item: D) {

            val radioButton = binding.root.findViewById<AppCompatRadioButton>(getRadioButtonId())
            radioButton.setOnClickListener {
                itemClickListener.onItemClicked(item)
            }

            radioButton.isChecked = (item as Reasons).id == selectedItemId

            binding.apply {
                setVariable(getItemBindingVariable(), item)
                executePendingBindings()
            }
        }
    }

}