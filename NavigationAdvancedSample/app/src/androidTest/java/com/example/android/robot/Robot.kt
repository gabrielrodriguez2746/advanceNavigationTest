package com.example.android.robot

import android.app.Activity
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import com.jraska.falcon.FalconSpoonRule
import org.hamcrest.Matcher

typealias ViewMatcher = Matcher<View>

abstract class Robot {

    val click: ViewAction = ViewActions.click()
    val displayed: ViewMatcher = ViewMatchers.isDisplayed()

    lateinit var falconRule: FalconSpoonRule

    protected abstract val robotName: String

    private val tagRegex = "[^a-zA-Z0-9_-]+".toRegex()

    fun takeScreenshot(description: String) {
        val currentActivity = getCurrentActivityInstance()
        val validTag = tagRegex.replace("$robotName $description", "_")
        falconRule.screenshot(currentActivity, validTag)
    }

    fun type(text: String): ViewAction = typeText(text)

    infix fun ViewAction.on(id: Int) {
        onView(withId(id)).perform(this)
    }

    infix fun ViewAction.on(text: String) {
        onView(withText(text)).perform(this)
    }

    infix fun ViewAction.into(id: Int) {
        onView(withId(id)).perform(this)
    }

    infix fun Int.shouldBe(matcher: ViewMatcher) {
        onView(withId(this)).check(ViewAssertions.matches(matcher))
    }

    infix fun String.shouldBe(matcher: ViewMatcher) {
        onView(withText(this)).check(ViewAssertions.matches(matcher))
    }

    private fun getCurrentActivityInstance(): Activity? {
        val activity = arrayOfNulls<Activity>(1)
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            val activities = getActivitiesCollection()
            if (activities.isNotEmpty()) {
                activity[0] = activities.last()
            }
        }

        return activity[0]
    }

    private fun getActivitiesCollection(): Collection<Activity> {
        return (ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED).takeIf { it.isNotEmpty() }
                ?: ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.PAUSED))
    }

}
