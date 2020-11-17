package project.lonelywheeler.app

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import project.lonelywheeler.db.entity.user.UserEntity

@HiltAndroidApp
class MyApplication : Application() {

    companion object {
        var application: MyApplication? = null
        var currentUser: UserEntity? = null

        fun context(): Context {
            return application!!.applicationContext
        }

        fun getCurrentUserID() : String{
            return currentUser!!.id!!
        }

    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }
}