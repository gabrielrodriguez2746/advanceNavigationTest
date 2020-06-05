package com.example.android.robot

import androidx.test.espresso.matcher.ViewMatchers.withId

data class ViewElement(val matcher: ViewMatcher)

fun ViewElement(id: Int) = ViewElement(withId(id))
