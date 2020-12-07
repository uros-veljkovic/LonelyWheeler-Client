package project.lonelywheeler.util.extensions

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import project.lonelywheeler.util.constants.SIGN_BIGGER_THEN

fun TextInputEditText.observeNumberFieldError() {
    val container = parent.parent as TextInputLayout
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    })
}

fun TextInputEditText.observeComparison(
    sign: Int,
    otherEditText: TextInputEditText,
) {
    val container1: TextInputLayout = parent.parent as TextInputLayout
    val container2: TextInputLayout = otherEditText.parent.parent as TextInputLayout

    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            if (s.isNullOrEmpty() || (s.first() == '0' && s.length > 1)) {
                container1.error = container1.errorContentDescription
                return
            } else {
                container1.error = null
            }

            if (!s.isNullOrEmpty() && !otherEditText.text.isNullOrEmpty()) {
                val value1 = s.toString().toInt()
                val value2 = otherEditText.text.toString().toInt()

                val biggerValue: Int
                val lowerValue: Int
                if (sign == SIGN_BIGGER_THEN) {
                    biggerValue = value1
                    lowerValue = value2
                } else {
                    lowerValue = value1
                    biggerValue = value2
                }
                if (lowerValue > biggerValue) {
                    container1.error = container1.errorContentDescription
                    container2.error = container1.errorContentDescription
                } else {
                    container1.error = null
                    container2.error = null
                }

            }

        }
    })
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