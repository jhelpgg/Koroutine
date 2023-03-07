package fr.jhelp.koroutine

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import fr.jhelp.koroutine.extensions.Draw
import fr.jhelp.koroutine.ui.theme.KoroutineTheme
import fr.jhelp.models.shared.NavigationModel
import fr.jhelp.models.shared.ViewModelTest
import fr.jhelp.tools.provider.provided
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch


/*
 coroutine 5 (suspend)

----- coroutine 1 (suspend)

------ coroutine 2

------ coroutine 3

------- coroutine 6


 */


class MainActivity : ComponentActivity()
{
    private val scope =  CoroutineScope(Dispatchers.IO)
    private val scope2 = CoroutineScope(Dispatchers.IO)
    private val navigationModel: NavigationModel by provided<NavigationModel>()
    private val viewModelTest : ViewModelTest by this.viewModels<ViewModelTest>()
    private val mutableProgression : MutableStateFlow<Int> = MutableStateFlow(0)
    val progression : StateFlow<Int> = this.mutableProgression.asStateFlow()

    fun compute1() {
    }
    fun compute2() {
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        this.scope.launch {
            this@MainActivity.mutableProgression.value = 52

            val job1 = this@MainActivity.scope.launch { this@MainActivity.compute1() }
            val job2 = this@MainActivity.scope.launch { this@MainActivity.compute2() }
            // ...
            job1.join()
            job2.join()
            // ...
        }

        // private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        //        onError("Exception handled: ${throwable.localizedMessage}")
        //    }
        // CoroutineScope(Dispatchers.IO + exceptionHandler)

        this.scope.launch {
            this@MainActivity.progression.debounce(1L).collect { value ->
                // Each
            }
        }
        super.onCreate(savedInstanceState)
        this.setContent {
            KoroutineTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background) {
                        this@MainActivity.navigationModel.screen.value.Draw(this@MainActivity.viewModelTest)
                }
            }
        }

        this.onBackPressedDispatcher.addCallback(this, true) {
            if (this@MainActivity.navigationModel.back())
            {
                this@MainActivity.finish()
            }
        }

        this.lifecycleScope.launchWhenResumed {  }

        this.viewModelTest.name.observe(this){ string -> Log.d("REMOVE_ME", "Value = '$string'") }

        for (thread in 0 until 1000)
        {
           val job = this.scope.launch { this@MainActivity.test(thread) }
            job.invokeOnCompletion { exception -> }
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
            delay(1024L + ((Math.random() * 128L).toLong()))
            Log.d("REMOVE_ME",
                  "Start suspended $number : ${Thread.activeCount()} : AFTER DELAY : $time : ${Thread.currentThread().id} : ${Thread.currentThread().name}")
        }

        Log.d("REMOVE_ME",
              "END suspended $number : ${Thread.activeCount()} : ${Thread.currentThread().id} : ${Thread.currentThread().name}")
    }
}
