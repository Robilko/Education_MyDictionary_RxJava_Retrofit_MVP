package com.example.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase

/** Помечаем класс как @Database и наследуемся от RoomDatabase. Передаём список таблиц entities = arrayOf(HistoryEntity::class),
 * указываем версию БД (важно при миграции) и параметр exportSchema, который указывает, нужно ли хранить историю изменений в БД
 * в отдельном файле (полезно при совместной разработке)*/
@Database(entities = [HistoryEntity::class], version = 1, exportSchema = false)
abstract class HistoryDataBase : RoomDatabase() {
    /** Возвращаем DAO */
    abstract fun historyDao() : HistoryDao
}