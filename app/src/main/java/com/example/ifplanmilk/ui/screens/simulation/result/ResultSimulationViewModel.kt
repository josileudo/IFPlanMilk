package com.example.ifplanmilk.ui.screens.simulation.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ifplanmilk.data.entities.IFPlanResultSimulationEntity
import com.example.ifplanmilk.data.entities.IFPlanSimulationEntity
import com.example.ifplanmilk.data.repository.SimulationRepository
import com.example.ifplanmilk.data.utils.SimulationMath
import com.example.ifplanmilk.ui.screens.simulation.animal.AnimalSimulationUiState
import com.example.ifplanmilk.ui.screens.simulation.area.AreaSimulationUiState
import com.example.ifplanmilk.ui.screens.simulation.climateSoil.ClimateSoilSimulationUiState
import com.example.ifplanmilk.ui.screens.simulation.economy.EconomySimulationUiState
import com.example.ifplanmilk.ui.screens.simulation.sliders.SlidersSimulationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ResultSimulationViewModel @Inject constructor(
    private val repository: SimulationRepository
) : ViewModel() {
    private var _uiState = MutableStateFlow(ResultSimulationUiState())
    var uiState: StateFlow<ResultSimulationUiState> = _uiState.asStateFlow()

    private var _eventFlow = Channel<ResultSimulationUiEvent>()
    var eventFlow: Flow<ResultSimulationUiEvent> = _eventFlow.receiveAsFlow()

    fun onEvent(event: ResultSimulationUiEvent) {
        when (event) {
            is ResultSimulationUiEvent.OnResultSimulation -> onResultSimulation(
                areaState = event.areaState,
                animalState = event.animalState,
                economyState = event.economyState,
                climateSoilState = event.climateSoilState,
                slidersState = event.slidersState
            )

            is ResultSimulationUiEvent.OnSaveSimulation -> onSaveSimulation(
                simulationTitle = event.simulationTitle,
                description = event.description,
                areaState = event.areaState,
                animalState = event.animalState,
                economyState = event.economyState,
                climateSoilState = event.climateSoilState,
                slidersState = event.slidersState
            )

            else -> {}
        }
    }
    private fun onResultSimulation(
        areaState: AreaSimulationUiState,
        animalState: AnimalSimulationUiState,
        economyState: EconomySimulationUiState,
        climateSoilState: ClimateSoilSimulationUiState,
        slidersState: SlidersSimulationUiState
    ) {
        val result = SimulationMath().simulate(
            areaState = areaState,
            animalState = animalState,
            economyState = economyState,
            climateSoilState = climateSoilState,
            slidersState = slidersState,
        )

        _uiState.update {
            it.copy(resultSimulation = result)
        }
    }

    private fun onSaveSimulation(
        simulationTitle: String,
        description: String,
        areaState: AreaSimulationUiState,
        animalState: AnimalSimulationUiState,
        economyState: EconomySimulationUiState,
        climateSoilState: ClimateSoilSimulationUiState,
        slidersState: SlidersSimulationUiState
    ) {
        val result = _uiState.value.resultSimulation
        val simulation = IFPlanSimulationEntity(
            title = simulationTitle,
            description = description,
            creationDate = Date().time,
            pesoCorporal = animalState.pesoCorporal,
            milkProduction = animalState.milkProduction,
            milkFatContent = animalState.milkFatContent,
            pbFatMilk = animalState.pbFatMilk,
            horizontalShift = animalState.horizontalShift,
            verticalShift = animalState.verticalShift,
            lactatingCows = animalState.lactatingCows,
            investmentsPerLiters = economyState.investmentsPerLiters,
            familyIncome = economyState.familyIncome,
            depreciationRate = economyState.depreciationRate,
            area = areaState.area,
            picketsNumber = areaState.picketsNumber,
            precipitation = climateSoilState.precipitation,
            maxTemperature = climateSoilState.maxTemperature,
            minTemperature = climateSoilState.minTemperature,
            relativeHumidity = climateSoilState.relativeHumidity,
            velocityVents = climateSoilState.velocityVents,
            nDosage = climateSoilState.nDosage,
            otherAndWater = climateSoilState.otherAndWater,
            waterAvailableForIrrigation = climateSoilState.waterAvailableToIrrigation
        )
        val resultEntity = IFPlanResultSimulationEntity(
            simulationId = 0,
            tenAguaSolo = result?.tenAguaSolo ?: 0.0,
            prodForragem = result?.prodForragem ?: 0.0,
            capaSuporte = result?.capaSuporte ?: 0.0,
            taxaLotacao = result?.taxaLotacao ?: 0.0,
            itu = result?.itu ?: 0.0,
            dpl = result?.dpl ?: 0.0,
            pegadaHidrica = result?.pegadaHidrica ?: 0.0,
            prodDiaria = result?.prodDiaria ?: 0.0,
            prodLeiteDia = result?.prodLeiteDia ?: 0.0,
            prodLeiteAno = result?.prodLeiteAno ?: 0.0,
            perdaReceitaEstresse = result?.perdaReceitaEstresse ?: 0.0,
            coe = result?.coe ?: 0.0,
            cot = result?.cot ?: 0.0,
            receitaTotalAno = result?.receitaTotalAno ?: 0.0,
            mlArea = result?.mlArea ?: 0.0,
            trci = result?.trci ?: 0.0,
            payback = result?.payback ?: 0.0,
        )

        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.saveSimulation(simulation = simulation, result = resultEntity)
                _eventFlow.send(ResultSimulationUiEvent.OnSuccessSimulationSaved)
            } catch(_: Exception) {
                _eventFlow.send(ResultSimulationUiEvent.OnFiledSimulationSaved)
            }
        }
    }
}