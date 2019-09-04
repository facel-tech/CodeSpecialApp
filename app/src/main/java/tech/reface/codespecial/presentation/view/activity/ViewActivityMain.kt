package tech.reface.codespecial.presentation.view.activity

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ViewActivityMain: MvpView {
    fun scrollUp()
    fun scrollDown()
    fun openIntro()
}