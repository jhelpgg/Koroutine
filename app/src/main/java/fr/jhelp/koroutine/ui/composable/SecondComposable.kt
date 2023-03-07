package fr.jhelp.koroutine.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import fr.jhelp.models.shared.SecondModel
import fr.jhelp.tools.provider.provided

object SecondComposable
{
    private val secondModel: SecondModel by provided<SecondModel>()

    @Composable
    fun Draw()
    {
        Column {
            Button(onClick = this@SecondComposable.secondModel::back) {
                Text(text = "Back")
            }
            Text(text = "Second screen")
            Button(onClick = this@SecondComposable.secondModel::action) {
                Text(text = "Action")
            }
        }
    }
}