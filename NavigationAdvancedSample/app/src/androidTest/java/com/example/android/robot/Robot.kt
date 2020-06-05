package com.example.android.robot

import android.app.Activity
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import com.jraska.falcon.FalconSpoonRule
import org.hamcrest.Matcher

typealias ViewMatcher = Matcher<View>

abstract class Robot {

    val click: RobotAction = RobotAction.Click
    val displayed: ViewMatcher = ViewMatchers.isDisplayed()

    lateinit var falconRule: FalconSpoonRule

    protected abstract val robotName: String

    private val tagRegex = "[^a-zA-Z0-9_-]+".toRegex()

    fun takeScreenshot(description: String) {
        val currentActivity = getCurrentActivityInstance()
        val validTag = tagRegex.replace("$robotName $description", "_")
        falconRule.screenshot(currentActivity, validTag)
    }

    fun type(text: String): RobotAction = RobotAction.TypeText(text)

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

    infix fun RobotAction.on(element: ViewElement) {
        onView(element.matcher).perform(*this.actions)
    }

    infix fun RobotAction.into(element: ViewElement) {
        onView(element.matcher).perform(*this.actions)
    }

    infix fun ViewElement.shouldBe(matcher: ViewMatcher) {
        onView(this.matcher).check(ViewAssertions.matches(matcher))
    }

}
