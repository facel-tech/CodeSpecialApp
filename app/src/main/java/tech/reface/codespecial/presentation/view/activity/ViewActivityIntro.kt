package tech.reface.codespecial.presentation.view.activity

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ViewActivityIntro : MvpView {
    fun setTitle(title: String)
    fun setDescription(description: String)
    fun setRightsButton(button: String)
    fun killActivity()
}