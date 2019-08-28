package com.example.android

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.navigationadvancedsample.MainActivity
import com.example.android.robot.home
import com.example.android.robot.register
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

class RegisterTest : SpoonTest() {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
        home { clickRegisterTab() }
    }

    @Test
    fun testRegister() {
        register {
            validateSelectAvatarIsDisplayed()
            enterCredentials("Gabriel",
                    "gabriel.rodriguez3103@gmail.com", "123456")
            signUp()
        }
    }

    @After
    fun tearDown() {
        scenario.close()
    }
}
