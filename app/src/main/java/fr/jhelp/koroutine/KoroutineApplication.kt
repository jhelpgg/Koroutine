package fr.jhelp.koroutine

import android.app.Application
import fr.jhelp.koroutine.ui.Navigation
import fr.jhelp.models.initializeModels
import fr.jhelp.models.shared.NavigationModel
import fr.jhelp.tools.provider.provideSingle

class KoroutineApplication : Application()
{
    override fun onCreate()
    {
        super.onCreate()
        val navigation =  Navigation()
        provideSingle<NavigationModel> { navigation }
        initializeModels(this)
        navigation.startApplication()
    }
}