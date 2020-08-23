package com.omersakalli.veripark

import com.omersakalli.veripark.util.AES_Functions
import com.omersakalli.veripark.util.DateCheck
import org.junit.Test

import org.junit.Assert.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UnitTests {
    @Test
    fun addition_isCorrect() {
        val current = LocalDateTime.now()

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm:ss.SSS") //2020-08-21T11:29:17+00:00
        val formatted = current.format(formatter)

        assertEquals(4, 2 + 2)
    }


    @Test
    fun datecheck_isWorkingCorrectly(){

        //2 thousand years from now should be valid
        assertTrue(DateCheck.isLifetimeValid("4020-08-21T11:29:17+00:00"))

        //1 thousand years ago should be invalid
        assertFalse(DateCheck.isLifetimeValid("1020-08-21T11:29:17+00:00"))

    }






}