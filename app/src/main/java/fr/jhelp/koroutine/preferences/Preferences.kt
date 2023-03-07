package fr.jhelp.koroutine.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import fr.jhelp.tools.provider.provided
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

object Preferences {
    private val EXAMPLE_COUNTER = intPreferencesKey("example_counter")
    private val scope = CoroutineScope(Dispatchers.IO)
    private val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(name = "settings")
    private val applicationContext : Context by provided<Context>()

    fun counter(value:Int) {
        this.scope.launch {
            this@Preferences.applicationContext.dataStore.edit { preferences -> preferences[this@Preferences.EXAMPLE_COUNTER] = value }
        }
    }
//
//    fun counter() : Int =
//        this@Preferences.applicationContext.dataStore.data.map { prefrences -> prefrences[this@Preferences.EXAMPLE_COUNTER] ?: 0 }.first()
}