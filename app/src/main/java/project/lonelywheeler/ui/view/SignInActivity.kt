package project.lonelywheeler.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import project.lonelywheeler.R
import project.lonelywheeler.ui.viewmodel.SignInViewModel

@AndroidEntryPoint
class SignInActivity : AppCompatActivity() {

    private val TAG = "SignInActivity"
    private val viewModel: SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        Log.d(TAG, "onCreate: ${viewModel.user}")
        Log.d(TAG, "onCreate: ${viewModel.user.accountInfo}")
        Log.d(TAG, "onCreate: ${viewModel.user.personalInfo}")


    }
}