package com.ulesson.takehome.di.modules.local

import androidx.room.Room
import com.ulesson.local.room.AppDatabase
import com.ulesson.local.room.dao.SubjectInfoDao
import com.ulesson.local.utils.LocalModuleConstants.DB_NAME
import com.ulesson.takehome.BaseApplication
import com.ulesson.takehome.di.scopes.AppScope
import dagger.Module
import dagger.Provides

@Module
class AppRoomModule {

    @AppScope
    @Provides
    fun provideDatabase(baseApplication: BaseApplication) = Room.databaseBuilder(
        baseApplication,
        AppDatabase::class.java, DB_NAME
    ).build()

    @Provides
    @AppScope
    fun provideSubjectInfoDao(database: AppDatabase): SubjectInfoDao =
        database.getSubjectInfoDao()
}
