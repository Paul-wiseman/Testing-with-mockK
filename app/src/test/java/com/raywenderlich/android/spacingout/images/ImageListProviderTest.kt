package com.raywenderlich.android.spacingout.images

import com.raywenderlich.android.spacingout.models.ApodImage
import com.raywenderlich.android.spacingout.network.SpacingOutApi
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.mock.Calls

class ImageListProviderTest{

    @Test
    fun `a list of 30 images is returned`() = runBlocking {
        val api = mockk<SpacingOutApi>()

        every { api.getImage(any()) } answers {
            Calls.response(
                ApodImage(
                    "08-11-2019",
                    "Test",
                    "www.testurl.com",
                    "test",
                    "www.testurl.com",
                    "video"
                )
            )
        }

        val provider = ImageListProvider(api)

        val images = provider.buildImageList()
        assertEquals(30, images.size)
    }
}