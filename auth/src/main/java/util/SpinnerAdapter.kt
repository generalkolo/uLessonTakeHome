package util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.gokada.ng.auth.R

/**
 * Created by edetebenezer on 2019-07-25
 **/

//TODO: Implement onClickListener
class SpinnerAdapter(
    context: Context, resource: Int,
    pickerItems: List<countryItem>,
    val onClickListener: OnClickListener
) :
    ArrayAdapter<countryItem>(context, resource, pickerItems) {

    private var items: List<countryItem> = pickerItems

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return spinnerItems(position, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return spinnerItems(position, parent)
    }

    private fun spinnerItems(position: Int, parent: ViewGroup): View {
        //Getting the Layout Inflater Service from the system
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        //Inflating out custom spinner view
        val customView = layoutInflater.inflate(R.layout.country_item, parent, false)
        //Declaring and initializing the widgets in custom layout
        val countryFlag = customView.findViewById(R.id.imgCountryFlag) as ImageView
        val countryCode = customView.findViewById(R.id.tvCountryName) as TextView
        customView.setOnClickListener {
            onClickListener.clickListener(items[position])
        }

        countryFlag.setImageResource(items[position].image!!)
        countryCode.text = items[position].code
        return customView
    }
}

class OnClickListener(val clickListener: (country: countryItem) -> Unit) {
    fun onClick(country: countryItem) = clickListener(country)
}