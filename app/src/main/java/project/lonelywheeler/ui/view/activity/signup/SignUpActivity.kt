package project.lonelywheeler.ui.view.activity.signup

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import bloder.com.blitzcore.enableWhenUsing
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_sign_up.*
import project.lonelywheeler.R
import project.lonelywheeler.databinding.ActivitySignUpBinding
import project.lonelywheeler.ui.view.activity.main.MainActivity
import project.lonelywheeler.ui.view.activity.signin.SignInActivity
import project.lonelywheeler.ui.viewmodel.auth.AuthViewModel
import project.lonelywheeler.util.constants.*
import project.lonelywheeler.util.validator.FieldValidator
import project.lonelywheeler.util.validator.matches

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
        binding.activitySignUpEtFirstName.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && !binding.activitySignUpEtFirstName.text?.matches(REGEX_FIRST_NAME)!!) {
                binding.activitySignUpTilFirstName.error = getString(R.string.error_first_name)
            } else {
                binding.activitySignUpTilFirstName.error = null
            }
        }

        binding.activitySignUpEtLastName.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && !binding.activitySignUpEtLastName.text?.matches(REGEX_LAST_NAME)!!) {
                binding.activitySignUpTilLastName.error = getString(R.string.error_last_name)
            } else {
                binding.activitySignUpTilLastName.error = null
            }
        }

        binding.activitySignUpEtUsername.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && !binding.activitySignUpEtUsername.text?.matches(REGEX_USERNAME)!!) {
                binding.activitySignUpTilUsername.error = getString(R.string.error_username)
            } else {
                binding.activitySignUpTilUsername.error = null
            }
        }

        binding.activitySignUpEtEmail.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && !binding.activitySignUpEtEmail.text?.matches(REGEX_EMAIL)!!) {
                binding.activitySignUpTilEmail.error = getString(R.string.error_email)
            } else {
                binding.activitySignUpTilEmail.error = null
            }
        }

        binding.activitySignUpEtCity.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && !binding.activitySignUpEtCity.text?.matches(REGEX_CITY_STREET)!!) {
                binding.activitySignUpTilCity.error = getString(R.string.error_city)
            } else {
                binding.activitySignUpTilCity.error = null
            }
        }

        binding.activitySignUpEtStreet.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && !binding.activitySignUpEtStreet.text?.matches(REGEX_CITY_STREET)!!) {
                binding.activitySignUpTilStreet.error = getString(R.string.error_street)
            } else {
                binding.activitySignUpTilStreet.error = null
            }
        }
    }

    private fun initSingUpEnabler() {
        binding.activitySignUpBtnSignUp.enableWhenUsing(FieldValidator()) {
            binding.activitySignUpEtUsername.isValidUsername()
            binding.activitySignUpEtFirstName.isValidFirstName()
            binding.activitySignUpEtLastName.isValidLastName()
            binding.activitySignUpEtEmail.isValidEmail()
            binding.activitySignUpEtUsername.isNotEmpty()
            binding.activitySignUpEtFirstName.isNotEmpty()
            binding.activitySignUpEtLastName.isNotEmpty()
            binding.activitySignUpEtEmail.isNotEmpty()
            binding.activitySignUpEtPassword matches binding.activitySignUpEtConfirmPassword
        }
    }

    override fun onResume() {
        super.onResume()

        initOnClickListener()
        initOnIvPictureClickListener()
        observeSignUpResult()
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
                showErrorMessage()
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

    private fun showErrorMessage() {
        Snackbar.make(
            activitySignUp_container,
            "${viewModel.authResponse?.message}",
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun startMainActivity() {
        Snackbar.make(
            activitySignUp_container,
            "${viewModel.authResponse?.message}",
            Snackbar.LENGTH_LONG
        ).show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
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