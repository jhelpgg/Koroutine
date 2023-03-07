package fr.jhelp.koroutine.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import fr.jhelp.models.shared.GreetingsModel
import fr.jhelp.models.shared.NavigationModel
import fr.jhelp.models.shared.Screen
import fr.jhelp.tools.provider.provided

class Navigation : NavigationModel
{
    private val greetingsModel: GreetingsModel by provided<GreetingsModel>()
    private var screenMutable: MutableState<Screen> =
        mutableStateOf<Screen>(Screen.UNDEFINED)
    override val screen: State<Screen> get() = this.screenMutable

    override fun startApplication()
    {
        this.changeScreen(Screen.GREETINGS)
    }

    override fun secondScreen()
    {
        this.changeScreen(Screen.SECOND)
    }

    override fun greetings(greetings: String)
    {
        this.greetingsModel.greetings(greetings)
    }

    override fun back(): Boolean
    {
        val screen =
            when (this.screenMutable.value)
            {
                Screen.SECOND -> Screen.GREETINGS
                else          -> return true
            }

        this.changeScreen(screen)
        return false
    }

    private fun changeScreen(screen: Screen)
    {
        this.screenMutable.value = screen
    }
}