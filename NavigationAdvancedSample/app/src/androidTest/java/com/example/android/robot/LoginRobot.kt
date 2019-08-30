package com.example.android.robot

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.android.SpoonTest
import com.example.android.navigationadvancedsample.R

class LoginRobot : Robot() {

    override val robotName: String = "login"

    fun enterCredentials(name: String,
                         email: String, password: String) {
        enterName(name)
        enterEmail(email)
        enterPassword(password)
    }

    private fun enterPassword(password: String) {
        onView(withId(R.id.password_text)).perform(click())
        onView(withId(R.id.password_text))
                .perform(typeText(password))
        Espresso.closeSoftKeyboard()
        takeScreenshot("enter password")
    }

    private fun enterEmail(email: String) {
        onView(withId(R.id.email_text)).perform(click())
        onView(withId(R.id.email_text))
                .perform(typeText(email))
        Espresso.closeSoftKeyboard()
        takeScreenshot("enter email")
    }

    private fun enterName(name: String) {
        onView(withId(R.id.username_text)).perform(click())
        onView(withId(R.id.username_text))
                .perform(typeText(name))
        Espresso.closeSoftKeyboard()
        takeScreenshot("enter name")

    }

    fun login() {
        onView(withText("LOGIN"))
                .perform(click())
        takeScreenshot("login")
    }

}

inline fun SpoonTest.login(func: LoginRobot.() -> Unit) = LoginRobot().applyRule().run {
    func()
}
