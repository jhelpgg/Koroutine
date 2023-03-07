package fr.jhelp.koroutine.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import fr.jhelp.models.shared.GreetingsModel
import fr.jhelp.tools.provider.provided

object GreetingsComposable
{
    private val greetingsModel: GreetingsModel by provided<GreetingsModel>()

    @Composable
    fun Draw()
    {
        Column {
            Text(text = this@GreetingsComposable.greetingsModel.greetings.value)
            Button(onClick = this@GreetingsComposable.greetingsModel::next) {
                Text(text = "Next")
            }
        }
    }
}