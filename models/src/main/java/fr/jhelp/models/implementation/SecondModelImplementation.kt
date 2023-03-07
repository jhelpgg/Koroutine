package fr.jhelp.models.implementation

import fr.jhelp.models.shared.NavigationModel
import fr.jhelp.models.shared.SecondModel
import fr.jhelp.tools.provider.provided

internal class SecondModelImplementation : SecondModel
{
    private val navigationModel: NavigationModel by provided<NavigationModel>()

    override fun action()
    {
        this.navigationModel.greetings("New greetings : ${Math.random()}")
    }

    override fun back()
    {
        this.navigationModel.back()
    }
}