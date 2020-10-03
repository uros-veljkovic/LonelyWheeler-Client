package project.lonelywheeler.model.domain.user

import dagger.hilt.android.scopes.ActivityRetainedScoped
import project.lonelywheeler.repository.entity.user.UserEntity
import javax.inject.Inject

@ActivityRetainedScoped
class User
@Inject
constructor(
    var id: Long?,
    val personalInfo: UserPersonalInfo,
    val accountInfo: UserAccountInfo
)

fun User.toEntity(): UserEntity {
    return UserEntity(
        id = this.id,
        personalInfoEntity = this.personalInfo.toEntity(),
        accountInfoEntity = this.accountInfo.toEntity()
    )
}