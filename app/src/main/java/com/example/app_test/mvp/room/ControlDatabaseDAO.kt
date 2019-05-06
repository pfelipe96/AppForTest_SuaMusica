package com.example.app_test.mvp.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.app_test.data.EventsData

@Dao
interface ControlDatabaseDAO{

    @Query("SELECT * FROM EventsDataFavorites")
    fun getAllFavorites() : List<EventsData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(vararg eventsData: EventsData)

    @Query("DELETE FROM EventsDataFavorites")
    fun deleteAllFavorites()

    @Query("DELETE FROM EventsDataFavorites WHERE id = :id")
    fun deleteOnlyFavorite(id: String)

    @Query("SELECT * FROM EventsDataFavorites WHERE id = :id")
    fun getOnlyFavorite(id: String) : EventsData
}