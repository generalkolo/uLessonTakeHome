package com.gokada.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gokada.local.BuildConfig
import com.gokada.local.models.auth.OnBoardingDoneLocalModel
import com.gokada.local.room.dao.OnBoardingStatusDao
import com.gokada.local.utils.converters.BooleanConverter
import com.gokada.local.utils.converters.BooleanListConverter
import com.gokada.local.utils.converters.DoubleListConverter

/**
 * Created by ayokunlepaul on 2019-07-17
 */
@Database(
    entities = [
        OnBoardingDoneLocalModel::class
    ],
    version = BuildConfig.VERSION_CODE,
    exportSchema = false
)
@TypeConverters(
    DoubleListConverter::class,
    BooleanListConverter::class,
    BooleanConverter::class
)
abstract class GokadaDatabase : RoomDatabase() {
    abstract fun getOnBoardingDao(): OnBoardingStatusDao

    companion object {
        @Volatile
        private var instance: GokadaDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            GokadaDatabase::class.java, "gokadaSuperApp.db"
        ).build()
    }
}