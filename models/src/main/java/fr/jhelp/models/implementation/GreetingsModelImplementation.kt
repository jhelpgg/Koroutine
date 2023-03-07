package fr.jhelp.models.implementation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import fr.jhelp.models.shared.GreetingsModel
import fr.jhelp.models.shared.NavigationModel
import fr.jhelp.tools.provider.provided

internal class GreetingsModelImplementation : GreetingsModel
{
    private val navigationModel: NavigationModel by provided<NavigationModel>()
    private var greetingsMutable: MutableState<String> = mutableStateOf<String>("Hello world !")
    override val greetings: State<String> get() = this.greetingsMutable

    override fun greetings(greetings: String)
    {
        if (greetings.isNotEmpty())
        {
            this.greetingsMutable.value = greetings
        }
    }

    override fun next()
    {
        this.navigationModel.secondScreen()
    }
}