package fr.jhelp.koroutine.ui.composable

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import fr.jhelp.models.shared.GreetingsModel
import fr.jhelp.models.shared.ViewModelTest
import fr.jhelp.tools.provider.provided


object GreetingsComposable
{
    private val applicationContext : Context by provided<Context>()
    private val greetingsModel: GreetingsModel by provided<GreetingsModel>()

    @Composable
    fun Draw(viewModelTest : ViewModelTest)
    {
        Column {
            Text(text = this@GreetingsComposable.greetingsModel.greetings.value+"\n"+viewModelTest.name.value)
            Button(onClick = this@GreetingsComposable.greetingsModel::next) {
                viewModelTest.name("Go next")
                Text(text = "Next")
            }
        }
    }
}