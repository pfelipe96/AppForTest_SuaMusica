package com.example.app_test.mvp.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.example.app_test.data.EventsData
import com.example.app_test.data.SeatGeekData
import com.example.app_test.utils.Converters

@Database(entities = [EventsData::class], version = 1)
@TypeConverters(Converters::class)
abstract class FavoriteDatabase: RoomDatabase(){
    abstract fun controlDatabaseDAO() : ControlDatabaseDAO
}