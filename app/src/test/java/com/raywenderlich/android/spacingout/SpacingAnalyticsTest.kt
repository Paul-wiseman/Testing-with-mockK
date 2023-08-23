package com.raywenderlich.android.spacingout

import com.raywenderlich.android.spacingout.lookup.LookupViewModel
import com.raywenderlich.android.spacingout.models.EarthImage
import com.raywenderlich.android.spacingout.network.SpacingOutApi
import io.mockk.coEvery
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.runs
import io.mockk.slot
import junit.framework.TestCase
import org.junit.Test

class SpacingAnalyticsTest{

    @Test
    fun `New analytic argument are added before events are sent along`(){
        mockkObject(ThirdPartyAnalyticsProvider)

        val slot = slot<Map<String, String>>()

        every { ThirdPartyAnalyticsProvider.logEvent(any(), capture(slot)) } just runs

      SpacingAnalytics().logEvent("Test", mapOf("attribute" to "value"))

        val expected = mapOf("attribute" to "value",
            "client_type" to "Android",
            "version" to "1")

        TestCase.assertEquals(expected, slot.captured)
    }
}