package project.lonelywheeler.app

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import project.lonelywheeler.db.entity.user.UserEntity
import project.lonelywheeler.model.domain.user.User

@HiltAndroidApp
class MyApplication : Application() {

    companion object {
        var application: MyApplication? = null
        var currentUser : UserEntity? = null

        fun context(): Context {
            return application!!.applicationContext
        }
    }


    override fun onCreate() {
        super.onCreate()
        application = this
    }
}