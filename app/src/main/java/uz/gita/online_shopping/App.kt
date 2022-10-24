package uz.gita.online_shopping

import android.app.Application
import android.content.res.Configuration
import android.util.DisplayMetrics
import androidx.viewbinding.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import java.util.*

// Created by Jamshid Isoqov an 10/9/2022
@HiltAndroidApp
class App : Application() {

    companion object {
        lateinit var instance: App
    }
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
       /* val res = this.resources
        val dm: DisplayMetrics = res.displayMetrics
        val conf: Configuration = res.configuration
        conf.setLocale(Locale("ru"))
        res.updateConfiguration(conf, dm)*/
        instance = this
    }
}