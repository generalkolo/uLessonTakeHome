package com.ulesson.local.utils.converters

import androidx.room.TypeConverter
import com.ulesson.local.models.auth.LessonsLocalItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LessonsLocalItemConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun fromWeeklyRewardsList(value: List<LessonsLocalItem>?): String? {
            if (value == null) {
                return null
            }
            return Gson().toJson(value)
        }

        @TypeConverter
        @JvmStatic
        fun toWeeklyRewardsList(value: String?): List<LessonsLocalItem>? {
            if (value == null) {
                return null
            }
            val gson = Gson()
            val type = object : TypeToken<List<LessonsLocalItem>>() {}.type
            return gson.fromJson(value, type)
        }
    }
}
