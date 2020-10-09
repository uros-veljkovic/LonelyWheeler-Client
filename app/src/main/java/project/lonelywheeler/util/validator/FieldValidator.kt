package project.lonelywheeler.util.validator

import android.view.View
import bloder.com.blitzcore.validation.DefaultBlitzValidations
import com.google.android.material.textfield.TextInputEditText
import project.lonelywheeler.util.constants.REGEX_EMAIL
import project.lonelywheeler.util.constants.REGEX_FIRST_NAME
import project.lonelywheeler.util.constants.REGEX_LAST_NAME
import project.lonelywheeler.util.constants.REGEX_USERNAME

class FieldValidator : DefaultBlitzValidations() {


    fun TextInputEditText.isValidUsername(): View = bindViewValidation(this) {
        this.text?.matches(REGEX_USERNAME)!!
    }

    fun TextInputEditText.isValidEmail(): View = bindViewValidation(this) {
        this.text?.matches(REGEX_EMAIL)!!
    }

    fun TextInputEditText.isValidFirstName(): View = bindViewValidation(this) {
        this.text?.matches(REGEX_FIRST_NAME)!!
    }

    fun TextInputEditText.isValidLastName(): View = bindViewValidation(this) {
        this.text?.matches(REGEX_LAST_NAME)!!
    }

    fun TextInputEditText.isNotEmpty(): View = bindViewValidation(this) {
        !this.text?.isEmpty()!!
    }


}

infix fun TextInputEditText.matches(et: TextInputEditText): Boolean {
    return this.text!!.equals(et.text!!)
}