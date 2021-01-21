package com.ulesson.local.utils.converters

import androidx.room.TypeConverter
import com.ulesson.local.models.auth.ChaptersLocalItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ChaptersLocalItemConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun fromWeeklyRewardsList(value: List<ChaptersLocalItem>?): String? {
            if (value == null) {
                return null
            }
            return Gson().toJson(value)
        }

        @TypeConverter
        @JvmStatic
        fun toWeeklyRewardsList(value: String?): List<ChaptersLocalItem>? {
            if (value == null) {
                return null
            }
            val gson = Gson()
            val type = object : TypeToken<List<ChaptersLocalItem>>() {}.type
            return gson.fromJson(value, type)
        }
    }
}
