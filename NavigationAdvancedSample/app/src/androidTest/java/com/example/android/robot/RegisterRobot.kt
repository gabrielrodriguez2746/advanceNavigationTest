package com.example.android.robot

import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.android.SpoonTest
import com.example.android.navigationadvancedsample.R

class RegisterRobot : Robot() {

    override val robotName: String = "register"

    companion object ViewElements {
        private val PASSWORD_FIELD = ViewElement(R.id.password_text)
        private val USERNAME_FIELD = ViewElement(R.id.username_text)
        private val EMAIL_FIELD = ViewElement(R.id.email_text)
        private val SIGN_UP_BUTTON = ViewElement(withText(R.string.sign_up))
        private val SELECT_AVATAR_TITLE = ViewElement(withText(R.string.select_an_avatar))
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
        type(password) into PASSWORD_FIELD
        takeScreenshot("enter password")
    }

    private fun enterEmail(email: String) {
        type(email) into EMAIL_FIELD
        takeScreenshot("enter email")
    }

    private fun enterName(name: String) {
        type(name) into USERNAME_FIELD
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
