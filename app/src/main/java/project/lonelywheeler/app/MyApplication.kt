package project.lonelywheeler.app

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import project.lonelywheeler.db.entity.user.UserEntity
import project.lonelywheeler.util.ip.adress.WifiUtils

@HiltAndroidApp
class MyApplication : Application() {

    companion object {
        var application: MyApplication? = null
        var currentUser: UserEntity? = null
        lateinit var ip : String;

        fun context(): Context {
            return application!!.applicationContext
        }

        val currentUserId by lazy{
            currentUser!!.id
        }
    }


    override fun onCreate() {
        super.onCreate()
        application = this
        val ipp = WifiUtils.getLocalIPv4()
        ip = ipp
    }
}