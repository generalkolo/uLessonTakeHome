package com.gokada.local.utils.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by edetebenezer on 2019-09-17
 **/
class BooleanConverter{
    companion object {
        @TypeConverter
        @JvmStatic
        fun fromBoolean(value: Boolean?): String? {
            if (value == null) {
                return null
            }
            return Gson().toJson(value)
        }

        @TypeConverter
        @JvmStatic
        fun toBoolean(value: String?): Boolean? {
            if (value == null) {
                return null
            }
            val gson = Gson()
            val type = object : TypeToken<Boolean>() {}.type
            return gson.fromJson(value, type)
        }
    }
}