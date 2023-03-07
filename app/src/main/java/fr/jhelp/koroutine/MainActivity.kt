package fr.jhelp.koroutine

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import fr.jhelp.koroutine.extensions.Draw
import fr.jhelp.koroutine.ui.theme.KoroutineTheme
import fr.jhelp.models.shared.NavigationModel
import fr.jhelp.tools.provider.provided
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity()
{
    private val scope =  CoroutineScope(Dispatchers.Default)
    private val scope2 = CoroutineScope(Dispatchers.Default)
    private val navigationModel: NavigationModel by provided<NavigationModel>()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        this.setContent {
            KoroutineTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background) {
                    this.navigationModel.screen.value.Draw()
                }
            }
        }

        this.onBackPressedDispatcher.addCallback(this, true) {
            if (this@MainActivity.navigationModel.back())
            {
                this@MainActivity.finish()
            }
        }

        for (thread in 0 until 1000)
        {
            this.scope.launch { this@MainActivity.test(thread) }
            this.scope2.launch { this@MainActivity.test(10000 + thread) }
        }
    }

    private suspend fun test(number: Int)
    {
        Log.d("REMOVE_ME", "DELAY suspended $number : ${Thread.activeCount()} : ${Thread.currentThread().id} : ${Thread.currentThread().name}")

        for (time in 0 until 10)
        {
            Log.d("REMOVE_ME",
                  "Start suspended $number : ${Thread.activeCount()} : BEFORE DELAY : $time : ${Thread.currentThread().id} : ${Thread.currentThread().name}")
            Thread.sleep(128)
            delay(1024L + ((Math.random() * 128L).toLong()))
            Thread.sleep(128)
            Log.d("REMOVE_ME",
                  "Start suspended $number : ${Thread.activeCount()} : AFTER DELAY : $time : ${Thread.currentThread().id} : ${Thread.currentThread().name}")
        }

        Log.d("REMOVE_ME",
              "END suspended $number : ${Thread.activeCount()} : ${Thread.currentThread().id} : ${Thread.currentThread().name}")
    }
}
