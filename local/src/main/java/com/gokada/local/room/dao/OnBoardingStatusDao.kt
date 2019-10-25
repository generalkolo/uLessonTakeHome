package com.gokada.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gokada.local.models.auth.OnBoardingDoneLocalModel
import io.reactivex.Observable

/**
 * Created by edetebenezer on 2019-09-17
 **/
@Dao
interface OnBoardingStatusDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveOnBoardingStatus(status: OnBoardingDoneLocalModel)

    @Query("SELECT * FROM `GOKADA ONBOARDING COMPLETED`")
    fun getOnBoardingStatus(): Observable<OnBoardingDoneLocalModel>
}