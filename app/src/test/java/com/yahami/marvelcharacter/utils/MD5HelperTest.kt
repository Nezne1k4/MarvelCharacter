@file:Suppress("IllegalIdentifier")

package com.yahami.marvelcharacter.utils

import org.junit.Test

import org.junit.Assert.*

class MD5HelperTest {
    @Test
    fun `MD5 calculate correct case`() {
        val authParam = calculatedMd5("10000000PrivateKeyPublicKey")
        assertEquals("1746d867a61971a5f4a3f734401a95f8", authParam)
    }

}