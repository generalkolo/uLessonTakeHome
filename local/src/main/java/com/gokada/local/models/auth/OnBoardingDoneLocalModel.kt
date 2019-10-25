package com.gokada.local.models.auth

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by edetebenezer on 2019-09-17
 **/
@Entity(
    tableName = "GOKADA ONBOARDING COMPLETED"
)
class OnBoardingDoneLocalModel(
    @PrimaryKey var isCompleted : Boolean
)