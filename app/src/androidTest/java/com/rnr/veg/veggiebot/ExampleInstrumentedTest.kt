package com.rnr.veg.veggiebot

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented message_bubble_gren, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under message_bubble_gren.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.rnr.veg.veggiebot", appContext.packageName)
    }
}
