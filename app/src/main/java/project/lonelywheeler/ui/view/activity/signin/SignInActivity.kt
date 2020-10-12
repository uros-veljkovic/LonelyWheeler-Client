package project.lonelywheeler.ui.view.activity.signin

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.activitySignUp_container
import project.lonelywheeler.databinding.ActivitySignInBinding
import project.lonelywheeler.ui.view.activity.main.MainActivity
import project.lonelywheeler.ui.view.activity.signup.SignUpActivity
import project.lonelywheeler.ui.viewmodel.auth.AuthViewModel

@AndroidEntryPoint
class SignInActivity : AppCompatActivity() {

    private val viewModel: AuthViewModel by viewModels()
    lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewModel = viewModel
    }

    override fun onResume() {
        super.onResume()

        initOnClickListener()
        observeAuthentication()
    }

    private fun initOnClickListener() {
        binding.activitySignInTvSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    private fun observeAuthentication() {
        viewModel.authTrigger.observe(this, { successfulSignIn: Boolean? ->
            if (successfulSignIn!!) {
                startMainActivity()
            } else {
                showSnackbar()
            }
        })
    }

    private fun startMainActivity() {
        showSnackbar()
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun showSnackbar(){
        Snackbar.make(
            activitySignIn_container,
            "${viewModel.authResponse?.message}",
            Snackbar.LENGTH_LONG
        ).show()
    }
}