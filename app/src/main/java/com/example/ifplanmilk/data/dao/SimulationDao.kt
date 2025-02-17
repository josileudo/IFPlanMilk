package com.example.ifplanmilk.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.ifplanmilk.data.entities.IFPlanResultSimulationEntity
import com.example.ifplanmilk.data.entities.IFPlanSimulationEntity
import com.example.ifplanmilk.data.model.IFPlanSimulationWithResult

@Dao
interface SimulationDao {
    // Inserir uma simulação e retornar o ID gerado
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSimulation(simulation: IFPlanSimulationEntity): Long

    // Inserir um resultado de simulação
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResult(result: IFPlanResultSimulationEntity)

    // Obter todas as simulações com seus resultados
    @Transaction
    @Query("SELECT * FROM simulation_table")
    suspend fun getAllSimulationsWithResults(): List<IFPlanSimulationWithResult>

    // Deletar uma simulação
    @Delete
    suspend fun deleteSimulation(simulation: IFPlanSimulationEntity)

    // Deletar um resultado de simulação
    @Delete
    suspend fun deleteResult(result: IFPlanResultSimulationEntity)
}
