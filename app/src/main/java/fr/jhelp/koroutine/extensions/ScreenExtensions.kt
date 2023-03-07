package fr.jhelp.koroutine.extensions

import androidx.compose.runtime.Composable
import fr.jhelp.koroutine.ui.composable.GreetingsComposable
import fr.jhelp.koroutine.ui.composable.SecondComposable
import fr.jhelp.koroutine.ui.composable.UndefinedComposable
import fr.jhelp.models.shared.Screen
import fr.jhelp.models.shared.ViewModelTest

@Composable
inline fun Screen.Draw(viewModelTest : ViewModelTest) {
    when(this) {
        Screen.UNDEFINED -> UndefinedComposable.Draw()
        Screen.GREETINGS -> GreetingsComposable.Draw(viewModelTest)
        Screen.SECOND -> SecondComposable.Draw(viewModelTest)
    }
}