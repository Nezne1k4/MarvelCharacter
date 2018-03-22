package com.yahami.marvelcharacter

import com.yahami.marvelcharacter.utils.calculatedMd5
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by Nezneika on 3/22/18.
 */
class MD5HelperTest {
    @Test
    fun calculatedMd5_isCorrect() {
        val authParam = calculatedMd5("10000000PrivateKeyPublicKey")
        assertEquals("1746d867a61971a5f4a3f734401a95f8", authParam)
    }

}