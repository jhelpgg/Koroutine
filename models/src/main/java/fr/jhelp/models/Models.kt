package fr.jhelp.models

import android.content.Context
import fr.jhelp.models.implementation.GreetingsModelImplementation
import fr.jhelp.models.implementation.SecondModelImplementation
import fr.jhelp.models.shared.GreetingsModel
import fr.jhelp.models.shared.SecondModel
import fr.jhelp.tools.provider.provideSingle

fun initializeModels(context: Context)
{
    val applicationContext = context.applicationContext
    provideSingle<Context> { applicationContext }

    //

    provideSingle<GreetingsModel> { GreetingsModelImplementation() }
    provideSingle<SecondModel> { SecondModelImplementation() }
}