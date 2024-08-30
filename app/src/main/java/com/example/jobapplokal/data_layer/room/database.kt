package com.example.jobapplokal.data_layer.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.jobapplokal.data_layer.room.converter.Converter

@Database(entities = [JobResult::class], version = 3)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun resultDao(): JobDao
}
