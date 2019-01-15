package com.istore.twitsplit

import android.app.Application
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.istore.twitsplit.addtwit.AddTwitViewModel
import com.istore.twitsplit.addtwit.Split

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.util.ArrayList

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private lateinit var split : Split

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.istore.twitsplit", appContext.packageName)
    }


    @Test
    fun lessThen50CharCheck() {
        split = mock(Split::class.java)
        val list : ArrayList<String> = ArrayList<String>()
        list.add("ABCD")
        assertEquals(split.splitMessage("ABCD"), list)
    }
}
