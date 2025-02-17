package com.example.ifplanmilk.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ifplanmilk.data.dao.SimulationDao
import com.example.ifplanmilk.data.entities.IFPlanResultSimulationEntity
import com.example.ifplanmilk.data.entities.IFPlanSimulationEntity

@Database(
    entities = [IFPlanSimulationEntity::class, IFPlanResultSimulationEntity::class],
    version = 1,
    exportSchema = false
)

abstract class IFPlanSimulationDatabase : RoomDatabase() {

    abstract fun simulationDao(): SimulationDao

    companion object {
        @Volatile
        private var INSTANCE: IFPlanSimulationDatabase? = null

        fun getDatabase(context: Context): IFPlanSimulationDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    IFPlanSimulationDatabase::class.java,
                    "simulation_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}