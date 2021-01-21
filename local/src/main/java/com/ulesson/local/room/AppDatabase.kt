package com.ulesson.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ulesson.local.BuildConfig
import com.ulesson.local.models.auth.SubjectsLocalItem
import com.ulesson.local.room.dao.SubjectInfoDao
import com.ulesson.local.utils.converters.*

@Database(
    entities = [
        SubjectsLocalItem::class
    ],
    version = BuildConfig.VERSION_CODE,
    exportSchema = false
)
@TypeConverters(
    LessonsLocalItemConverter::class,
    ChaptersLocalItemConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getSubjectInfoDao(): SubjectInfoDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "gokadaSuperApp.db"
        ).build()
    }
}
