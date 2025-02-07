package com.example.ifplanmilk.data.model.mock

import androidx.compose.material3.Text
import com.example.ifplanmilk.data.model.IFPlanStepSimulation

val IFPlanStepSimulationMock: List<IFPlanStepSimulation> = listOf(
    IFPlanStepSimulation(
        title = "Animal",
        content = {
            Text("Animal screen")
        }
    ),
    IFPlanStepSimulation(
        title = "Area",
        content = {
            Text("Area screen")
        }
    ),
    IFPlanStepSimulation(
        title = "Economia",
        content = {
            Text("Economia screen")
        }
    ),
    IFPlanStepSimulation(
        title = "Clima e Solo",
        content = {
            Text("Clima e Solo screen")
        }
    )
)