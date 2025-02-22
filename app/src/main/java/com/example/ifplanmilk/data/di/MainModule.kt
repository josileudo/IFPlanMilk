package com.example.ifplanmilk.data.di

import android.content.Context
import com.example.ifplanmilk.data.dao.SimulationDao
import com.example.ifplanmilk.data.db.IFPlanSimulationDatabase
import com.example.ifplanmilk.data.repository.WelcomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MainModule {

    @Provides
    @Singleton
    fun provideWelcomeRepository(
        @ApplicationContext context: Context
    ) = WelcomeRepository(context = context)

    @Provides
    fun provideAnimalDao(database: IFPlanSimulationDatabase): SimulationDao {
        return database.simulationDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): IFPlanSimulationDatabase {
        return IFPlanSimulationDatabase.getDatabase(context)
    }
}