package fr.jhelp.koroutine.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import fr.jhelp.models.shared.SecondModel
import fr.jhelp.models.shared.ViewModelTest
import fr.jhelp.tools.provider.provided

object SecondComposable
{
    private val secondModel: SecondModel by provided<SecondModel>()

    @Composable
    fun Draw(viewModelTest : ViewModelTest)
    {
        Column {
            Button(onClick = this@SecondComposable.secondModel::back) {
                Text(text = "Back")
            }
            Text(text = "Second screen\n"+viewModelTest.name.value)
            Button(onClick = this@SecondComposable.secondModel::action) {
                viewModelTest.name("Action")
                Text(text = "Action")
            }
        }
    }
}