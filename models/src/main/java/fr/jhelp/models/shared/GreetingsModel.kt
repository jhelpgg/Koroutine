package fr.jhelp.models.shared

import androidx.compose.runtime.State

interface GreetingsModel
{
    val greetings : State<String>
    fun greetings(greetings:String)
    fun next()
}