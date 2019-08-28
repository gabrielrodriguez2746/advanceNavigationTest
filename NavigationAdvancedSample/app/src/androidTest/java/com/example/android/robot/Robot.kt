package com.example.android.robot

import android.app.Activity
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import com.jraska.falcon.FalconSpoonRule

abstract class Robot {

    lateinit var falconRule: FalconSpoonRule

    protected abstract val robotName: String

    private val tagRegex = "[^a-zA-Z0-9_-]+".toRegex()

    fun takeScreenshot(description: String) {
        val currentActivity = getCurrentActivityInstance()
        val validTag = tagRegex.replace("$robotName $description", "_")
        falconRule.screenshot(currentActivity, validTag)
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
