package com.example.ifplanmilk.data.repository

import com.example.ifplanmilk.data.dao.SimulationDao
import com.example.ifplanmilk.data.entities.IFPlanResultSimulationEntity
import com.example.ifplanmilk.data.entities.IFPlanSimulationEntity
import com.example.ifplanmilk.data.model.IFPlanSimulationWithResult
import javax.inject.Inject

class SimulationRepository @Inject constructor(private val dao: SimulationDao) {
    suspend fun saveSimulation(simulation: IFPlanSimulationEntity, result: IFPlanResultSimulationEntity) {
        try {
            val simulationId = dao.insertSimulation(simulation)
            dao.insertResult(result.copy(simulationId = simulationId))
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun getAllSimulations(): List<IFPlanSimulationWithResult> {
        return dao.getAllSimulationsWithResults()
    }

    suspend fun deleteSimulation(simulation: IFPlanSimulationEntity) {
        dao.deleteSimulation(simulation)
    }
}