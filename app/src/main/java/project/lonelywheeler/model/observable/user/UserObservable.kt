package project.lonelywheeler.model.observable.user

import androidx.databinding.BaseObservable
import com.google.gson.annotations.SerializedName
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityRetainedScoped
import project.lonelywheeler.db.entity.user.UserEntity
import javax.inject.Inject

@ActivityRetainedScoped
class UserObservable
@Inject
constructor() : BaseObservable() {
    @SerializedName("_id")
    var id: String? = null
    var personalInfoObservable: UserPersonalInfoObservable = UserPersonalInfoObservable()
    var accountInfoObservable: UserAccountInfoObservable = UserAccountInfoObservable()

    fun toEntity(): UserEntity {
        return UserEntity(
            id,
            personalInfoObservable.toEntity(),
            accountInfoObservable.toEntity()
        )
    }
}