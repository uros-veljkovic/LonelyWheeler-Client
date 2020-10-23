package project.lonelywheeler.ui.view.activity.signin

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import project.lonelywheeler.databinding.ActivitySignInBinding
import project.lonelywheeler.ui.view.activity.main.MainActivity
import project.lonelywheeler.ui.view.activity.signup.SignUpActivity
import project.lonelywheeler.ui.viewmodel.auth.AuthViewModel
import java.util.*
import kotlin.concurrent.schedule

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
        observeProgressBarTrigger()
        observeAuthentication()
    }

    private fun observeProgressBarTrigger() {
        viewModel.progressBarTrigger.observe(this, { triggered: Boolean? ->
            if (triggered!!)
                binding.activitySignInProgressBar.visibility = View.VISIBLE
            else
                binding.activitySignInProgressBar.visibility = View.GONE
        })
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
        Timer().schedule(700) {
            GlobalScope.launch {
                withContext(Dispatchers.Main) {
                    startActivity(Intent(this@SignInActivity, MainActivity::class.java))
                }
            }
        }
    }

    private fun showSnackbar() {
        Snackbar.make(
            activitySignIn_container,
            "${viewModel.authResponse?.message}",
            Snackbar.LENGTH_LONG
        ).show()
    }
}