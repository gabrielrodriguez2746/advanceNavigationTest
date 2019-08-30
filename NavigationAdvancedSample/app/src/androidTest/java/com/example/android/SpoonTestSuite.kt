package com.example.android

import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

@RunWith(Suite::class)
@SuiteClasses(
        E2ERegisterTest::class, RegisterTest::class, RegisterTargetedTest::class
)
class SpoonTestSuite