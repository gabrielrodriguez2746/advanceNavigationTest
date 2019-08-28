package com.example.android

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.GrantPermissionRule
import androidx.test.rule.GrantPermissionRule.grant
import com.example.android.robot.Robot
import com.jraska.falcon.FalconSpoonRule
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
abstract class SpoonTest {

    @get:Rule
    val falconSpoonRule: FalconSpoonRule = FalconSpoonRule()

    @get:Rule
    val storagePermissionRule: GrantPermissionRule = grant(WRITE_EXTERNAL_STORAGE)

    fun <T : Robot> T.applyRule(): T {
        falconRule = falconSpoonRule
        return this
    }

}
