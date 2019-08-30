package com.example.android.robot

import androidx.test.espresso.Espresso.closeSoftKeyboard
import com.example.android.SpoonTest
import com.example.android.navigationadvancedsample.R

class RegisterRobot : Robot() {

    override val robotName: String = "register"

    companion object {
        private const val PASSWORD_FIELD = R.id.password_text
        private const val USERNAME_FIELD = R.id.username_text
        private const val EMAIL_FIELD = R.id.email_text
        private const val SIGN_UP_BUTTON = "SIGN UP"
        private const val SELECT_AVATAR_TITLE = "Select an Avatar"
    }

    fun validateSelectAvatarIsDisplayed() {
        SELECT_AVATAR_TITLE shouldBe displayed
    }

    fun enterCredentials(name: String,
                         email: String, password: String) {
        enterName(name)
        enterEmail(email)
        enterPassword(password)
    }

    private fun enterPassword(password: String) {
        click on PASSWORD_FIELD
        type(password) into PASSWORD_FIELD
        closeSoftKeyboard()
        takeScreenshot("enter password")
    }

    private fun enterEmail(email: String) {
        click on EMAIL_FIELD
        type(email) into EMAIL_FIELD
        closeSoftKeyboard()
        takeScreenshot("enter email")
    }

    private fun enterName(name: String) {
        click on USERNAME_FIELD
        type(name) into USERNAME_FIELD
        closeSoftKeyboard()
        takeScreenshot("enter name")

    }

    fun signUp() {
        click on SIGN_UP_BUTTON
        takeScreenshot("sign up")
    }

}

inline fun SpoonTest.register(func: RegisterRobot.() -> Unit) = RegisterRobot().applyRule().run {
    func()
}
