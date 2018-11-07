package com.imageLoader.nikita.imageLoader

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val m = HashMap<String, String>()
        m.put("1", "11")
        m.put("2", "122")
        assertEquals("0", m.get("3") ?: "0")
    }
}
