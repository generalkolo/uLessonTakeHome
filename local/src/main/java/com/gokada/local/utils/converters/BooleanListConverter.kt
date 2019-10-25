package com.gokada.local.utils.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by edetebenezer on 2019-08-22
 **/
class BooleanListConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun fromBooleanList(value: List<Boolean>?): String? {
            if (value == null) {
                return null
            }
            return Gson().toJson(value)
        }

        @TypeConverter
        @JvmStatic
        fun toBooleanList(value: String?): List<Boolean>? {
            if (value == null) {
                return null
            }
            val gson = Gson()
            val type = object : TypeToken<List<Boolean>>() {}.type
            return gson.fromJson(value, type)
        }
    }
}