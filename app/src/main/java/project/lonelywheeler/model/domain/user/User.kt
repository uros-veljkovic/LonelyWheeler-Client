package project.lonelywheeler.model.domain.user

import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class User
@Inject
constructor(
    var id: Long?,
    val personalInfo: UserPersonalInfo,
    val accountInfo: UserAccountInfo
)