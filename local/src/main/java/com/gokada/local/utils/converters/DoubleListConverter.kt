package com.gokada.local.utils.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by edetebenezer on 2019-08-22
 **/
class DoubleListConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun fromDoubleList(value: List<Double>?): String? {
            if (value == null) {
                return null
            }
            return Gson().toJson(value)
        }

        @TypeConverter
        @JvmStatic
        fun toDoubleList(value: String?): List<Double>? {
            if (value == null) {
                return null
            }
            val gson = Gson()
            val type = object : TypeToken<List<Double>>() {}.type
            return gson.fromJson(value, type)
        }
    }
}