package project.lonelywheeler.app

import android.app.Application
import android.content.Context

class MyApplication : Application() {

    companion object {
        var application: MyApplication? = null

        fun context(): Context {
            return application!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }
}