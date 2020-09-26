package project.lonelywheeler.ui.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import project.lonelywheeler.model.domain.user.User

class SignInViewModel
@ViewModelInject
constructor(
    val user: User,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    init {
        user.toString()
    }



}