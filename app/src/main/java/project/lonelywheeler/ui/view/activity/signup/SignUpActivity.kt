package project.lonelywheeler.ui.view.activity.signup

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import bloder.com.blitzcore.enableWhenUsing
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import project.lonelywheeler.R
import project.lonelywheeler.databinding.ActivitySignUpBinding
import project.lonelywheeler.ui.view.activity.main.MainActivity
import project.lonelywheeler.ui.view.activity.signin.SignInActivity
import project.lonelywheeler.ui.viewmodel.auth.AuthViewModel
import project.lonelywheeler.util.constants.*
import project.lonelywheeler.util.validator.FieldValidator
import project.lonelywheeler.util.validator.matches
import java.util.*
import kotlin.concurrent.schedule

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {

    private val viewModel: AuthViewModel by viewModels()
    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewModel = viewModel

        initSingUpEnabler()
        initEditTextErrorListeners()
    }

    private fun initEditTextErrorListeners() {

        binding.apply {
            activitySignUpEtFirstName.observeErrorFromRegex(
                activitySignUpTilFirstName,
                REGEX_FIRST_NAME
            )
            activitySignUpEtLastName.observeErrorFromRegex(
                activitySignUpTilLastName,
                REGEX_LAST_NAME
            )
            activitySignUpEtUsername.observeErrorFromRegex(
                activitySignUpTilUsername,
                REGEX_USERNAME
            )
            activitySignUpEtEmail.observeErrorFromRegex(
                activitySignUpTilEmail,
                REGEX_EMAIL
            )
            activitySignUpEtCity.observeErrorFromRegex(
                activitySignUpTilCity,
                REGEX_CITY_STREET
            )
            activitySignUpEtStreet.observeErrorFromRegex(
                activitySignUpTilStreet,
                REGEX_CITY_STREET
            )

        }

        binding.activitySignUpEtConfirmPassword.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && !binding.activitySignUpEtConfirmPassword.equals(binding.activitySignUpEtConfirmPassword)) {
                binding.activitySignUpTilConfirmPassword.error =
                    getString(R.string.error_confirm_password)
            } else {
                binding.activitySignUpTilConfirmPassword.error = null
            }
        }
    }

    private fun initSingUpEnabler() {
        binding.activitySignUpBtnSignUp.enableWhenUsing(FieldValidator()) {
            binding.apply {
                activitySignUpEtUsername.isValidUsername()
                activitySignUpEtFirstName.isValidFirstName()
                activitySignUpEtLastName.isValidLastName()
                activitySignUpEtEmail.isValidEmail()
                activitySignUpEtUsername.isNotEmpty()
                activitySignUpEtFirstName.isNotEmpty()
                activitySignUpEtLastName.isNotEmpty()
                activitySignUpEtEmail.isNotEmpty()
                activitySignUpEtPassword matches binding.activitySignUpEtConfirmPassword
            }
        }
    }

    override fun onResume() {
        super.onResume()

        initOnClickListener()
        initOnIvPictureClickListener()
        observeProgressBarTrigger()
        observeSignUpResult()
    }

    private fun observeProgressBarTrigger() {
        viewModel.progressBarTrigger.observe(this, { triggered: Boolean? ->
            if (triggered!!)
                binding.activitySignUpProgressBar.visibility = View.VISIBLE
            else
                binding.activitySignUpProgressBar.visibility = View.GONE
        })
    }

    private fun initOnClickListener() {
        binding.activitySignUpTvSignIn.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }

    private fun observeSignUpResult() {
        viewModel.authTrigger.observe(this, { successfulSignUp: Boolean? ->
            if (successfulSignUp!!) {
                startMainActivity()
            } else {
                showSnackbar(viewModel.authResponse?.message)
            }
        })
    }

    private fun initOnIvPictureClickListener() {
        binding.activitySignUpSivPicture.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, INTENT_REQUEST_CODE_IMAGE)
        }
    }

    private fun showSnackbar(message: String?) {
        Snackbar.make(
            activitySignUp_container,
            message ?: "No message",
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun startMainActivity() {
        showSnackbar(viewModel.authResponse?.message)
        Timer().schedule(700) {
            GlobalScope.launch {
                withContext(Dispatchers.Main) {
                    val intent = Intent(this@SignUpActivity, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == INTENT_REQUEST_CODE_IMAGE) {
            binding.activitySignUpSivPicture.setImageURI(data?.data) // handle chosen image
            viewModel.user.accountInfo.picture =
                binding.activitySignUpSivPicture.drawable.toBitmap()
        }
    }


}


fun TextInputEditText.observeErrorFromRegex(inputLayout: TextInputLayout, regex: Regex) {
    this.setOnFocusChangeListener { _, hasFocus ->
        if (!hasFocus && !this.text?.matches(regex)!!) {
            inputLayout.error = inputLayout.errorContentDescription
        } else {
            inputLayout.error = null
        }
    }
}