package com.gokada.ng.di.modules.local

import androidx.room.Room
import com.gokada.local.room.GokadaDatabase
import com.gokada.local.room.dao.OnBoardingStatusDao
import com.gokada.local.utils.GokadaLocalModuleConstants.DB_NAME
import com.gokada.ng.BaseApplication
import com.gokada.ng.di.scopes.GokadaSuperAppScope
import dagger.Module
import dagger.Provides

/**
 * Created by edetebenezer on 2019-08-11
 **/

@Module
class GokadaSuperAppRoomModule {

    @GokadaSuperAppScope
    @Provides
    fun provideDatabase(baseApplication: BaseApplication) = Room.databaseBuilder(
        baseApplication,
        GokadaDatabase::class.java, DB_NAME
    ).build()

    @Provides
    @GokadaSuperAppScope
    fun provideOnBoardingDao(database: GokadaDatabase): OnBoardingStatusDao = database.getOnBoardingDao()
}