package fr.jhelp.koroutine.extensions

import androidx.compose.runtime.Composable
import fr.jhelp.koroutine.ui.composable.GreetingsComposable
import fr.jhelp.koroutine.ui.composable.SecondComposable
import fr.jhelp.koroutine.ui.composable.UndefinedComposable
import fr.jhelp.models.shared.Screen

@Composable
inline fun Screen.Draw() {
    when(this) {
        Screen.UNDEFINED -> UndefinedComposable.Draw()
        Screen.GREETINGS -> GreetingsComposable.Draw()
        Screen.SECOND -> SecondComposable.Draw()
    }
}