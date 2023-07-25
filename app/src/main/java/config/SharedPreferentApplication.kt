package config

import android.app.Application

class SharedPreferentApplication:Application() {
    companion object {
        lateinit var preferencia: prefs
    }
    override fun onCreate() {
        super.onCreate()
        preferencia= prefs(applicationContext)
    }


}