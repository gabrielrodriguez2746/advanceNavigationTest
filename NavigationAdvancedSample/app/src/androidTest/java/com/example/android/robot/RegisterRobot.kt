package com.example.android.robot

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.android.navigationadvancedsample.R

class RegisterRobot {
    fun validateSelectAvatarIsDisplayed() {
        onView(withText("Select an Avatar"))
                .check(matches(isDisplayed()))
    }

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
    }

    private fun enterEmail(email: String) {
        onView(withId(R.id.email_text)).perform(click())
        onView(withId(R.id.email_text))
                .perform(typeText(email))
        Espresso.closeSoftKeyboard()
    }

    private fun enterName(name: String) {
        onView(withId(R.id.username_text)).perform(click())
        onView(withId(R.id.username_text))
                .perform(typeText(name))
        Espresso.closeSoftKeyboard()

    }

    fun signUp() {
        onView(withText("SIGN UP"))
                .perform(click())
    }

}

inline fun register(func: RegisterRobot.() -> Unit) = RegisterRobot().run(func)
