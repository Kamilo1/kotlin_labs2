package pl.wsei.lab06

import android.app.Application
import pl.wsei.lab06.data.AppContainer
import pl.wsei.lab06.data.AppDataContainer

class TodoApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer.getInstance(applicationContext)

    }
}