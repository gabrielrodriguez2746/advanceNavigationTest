package com.example.android.robot

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.android.navigationadvancedsample.R

class HomeRobot {
    fun clickRegisterTab() {
        onView(withId(R.id.form))
                .perform(click())
    }

}

inline fun home(func: HomeRobot.() -> Unit) = HomeRobot().run(func)
