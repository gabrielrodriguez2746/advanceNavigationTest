package com.example.android

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.android.navigationadvancedsample.R
import com.example.android.navigationadvancedsample.formscreen.Register
import com.example.android.robot.register
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class RegisterTargetedTest : SpoonTest() {

    private lateinit var navController: NavController
    private lateinit var scenario: FragmentScenario<Register>

    @Before
    fun setup() {
        navController = mockk(relaxed = true)
        scenario = launchFragmentInContainer(themeResId = R.style.AppTheme)
        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
    }

    @Test
    fun register_segmented_test() {
        register {
            validateSelectAvatarIsDisplayed()
            enterCredentials("Gabriel",
                    "gabriel.rodriguez3103@gmail.com", "123456")
            signUp()
        }
    }

}
