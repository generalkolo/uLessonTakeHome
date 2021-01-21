package com.ulesson.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ulesson.local.models.auth.SubjectsLocalItem
import io.reactivex.Observable

@Dao
interface SubjectInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveSubjectInfo(subjectsInformation: List<SubjectsLocalItem>)

    @Query("SELECT * FROM `Subjects Information`")
    fun getSubjectInfo(): Observable<List<SubjectsLocalItem>>
}
