package com.jts.agrodei.acitivties.init

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.jts.agrodei.databinding.ActivityVerificationCodeBinding
import com.jts.agrodei.utils.Utility.DIGIT_COUNT

class VerificationCode : AppCompatActivity(), TextWatcher {
    private val editTextArray: ArrayList<EditText> = ArrayList(DIGIT_COUNT)
    private lateinit var binding: ActivityVerificationCodeBinding
    private var numTemp = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerificationCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        for (index in 0 until (binding.codeLayout.childCount)) {
            val view: View = binding.codeLayout.getChildAt(index)
            if (view is EditText) {
                editTextArray.add(index, view)
                editTextArray[index].addTextChangedListener(this)
            }
        }
        editTextArray[0].requestFocus()

        binding.verifyNow.setOnClickListener {
            verifyCode(testCodeValidity())
        }
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        numTemp = s.toString()
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun afterTextChanged(s: Editable?) {
        (0 until editTextArray.size)
            .forEach { i ->
                if (s === editTextArray[i].editableText) {

                    if (s!!.isBlank()) {
                        return
                    }
                    if (s.length >= 2) {
                        val newTemp = s.toString().substring(s.length - 1, s.length)
                        if (newTemp != numTemp) {
                            editTextArray[i].setText(newTemp)
                        } else {
                            editTextArray[i].setText(s.toString().substring(0, s.length - 1))
                        }
                    } else if (i != editTextArray.size - 1) {
                        editTextArray[i + 1].requestFocus()
                        editTextArray[i + 1].setSelection(editTextArray[i + 1].length())
                        return
                    } else {
                        verifyCode(testCodeValidity())
                    }
                }
            }
    }
    private fun enableCodeEditTexts(enable: Boolean) {
        for (i in 0 until editTextArray.size)
            editTextArray[i].isEnabled = enable
    }
    private fun testCodeValidity(): String {
        var verificationCode = ""
        for (i in editTextArray.indices) {
            val digit = editTextArray[i].text.toString().trim { it <= ' ' }
            verificationCode += digit
        }
        if (verificationCode.trim { it <= ' ' }.length == DIGIT_COUNT) {
            return verificationCode
        }
        return ""
    }
    private fun verifyCode(verificationCode: String) {
        if (verificationCode.isNotEmpty()) {
            enableCodeEditTexts(false)
            //Your code
        }
    }
    private fun initCodeEditTexts() {
        for (i in 0 until editTextArray.size)
            editTextArray[i].setText("")
        editTextArray[0].requestFocus()
    }

    private fun setVerificationCode(verificationCode: String) {
        for (i in 0 until editTextArray.size)
            editTextArray[i].setText(verificationCode.substring(i, i))
    }
}