package fr.jhelp.models.shared

import androidx.compose.runtime.State

interface NavigationModel
{
    val screen: State<Screen>

    fun startApplication()

    fun secondScreen()

    fun greetings(greetings:String)

    fun back() : Boolean
}