package com.example.android

import androidx.test.core.app.ActivityScenario
import com.example.android.navigationadvancedsample.login.LoginActivity
import com.example.android.robot.home
import com.example.android.robot.login
import com.example.android.robot.register
import org.junit.After
import org.junit.Before
import org.junit.Test

class E2ERegisterTest : SpoonTest() {

    private lateinit var scenario: ActivityScenario<LoginActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(LoginActivity::class.java)
        login {
            enterCredentials("Gabriel", "gabriel.rodriguez3103@gmail.com", "123456")
            login()
        }
        home { clickRegisterTab() }
    }

    @Test
    fun login_register_test() {
        register {
            validateSelectAvatarIsDisplayed()
            enterCredentials("Gabriel", "gabriel.rodriguez3103@gmail.com", "123456")
            signUp()
        }
    }

    @After
    fun tearDown() {
        scenario.close()
    }

}
