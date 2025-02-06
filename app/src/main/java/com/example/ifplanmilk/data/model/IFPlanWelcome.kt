package com.example.ifplanmilk.data.model

import androidx.annotation.DrawableRes
import com.example.ifplanmilk.R

sealed class Welcome (
    val image: Int,
    val title: String,
    val subtitle: String,
    val buttonText: String
) {
    data object Fist: Welcome (
        image = R.raw.mind_person,
        title = "Bem vindo ao IFPlanLeite",
        subtitle = "IFPlan Leite é o aplicativo ideal para produtores de leite que desejam aprimorar a gestão da produção com inteligência e precisão.",
        buttonText = "Próximo"
    )

    data object Second: Welcome(
        image = R.raw.management,
        title = "Gerencie da melhor forma",
        subtitle = "Desenvolvido especialmente para otimizar o planejamento e o uso dos recursos da propriedade, ele oferece uma interface prática e intuitiva",
        buttonText = "Próximo"
    )

    data object Third: Welcome(
        image = R.raw.productive,
        title = "Melhor controle",
        subtitle = "Permite o controle eficiente de diversos fatores que impactam a produtividade e a rentabilidade.",
        buttonText = "Iniciar"
    )
}
