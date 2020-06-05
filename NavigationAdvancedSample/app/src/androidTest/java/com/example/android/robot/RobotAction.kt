package com.example.android.robot

import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText

sealed class RobotAction(vararg val actions: ViewAction) {
    object Click : RobotAction(click())
    class TypeText(text: String) : RobotAction(click(), typeText(text), closeSoftKeyboard())
}