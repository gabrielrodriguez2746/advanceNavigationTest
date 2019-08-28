package com.example.android.robot

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.android.SpoonTest
import com.example.android.navigationadvancedsample.R

class HomeRobot : Robot() {

    override val robotName: String = "home"

    fun clickRegisterTab() {
        onView(withId(R.id.form))
                .perform(click())
        takeScreenshot("click register")
    }

}

inline fun SpoonTest.home(func: HomeRobot.() -> Unit) = HomeRobot().applyRule().run {
    func()
}
