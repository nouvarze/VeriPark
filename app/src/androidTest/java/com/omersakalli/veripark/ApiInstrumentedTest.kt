package com.omersakalli.veripark

import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.omersakalli.veripark.presentation.di.core.NetModule
import com.omersakalli.veripark.data.api.ImkbService
import com.omersakalli.veripark.util.AES_Functions
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ApiInstrumentedTest {
    @Test
    fun testHandshake() {

        /*val retrofit= NetModule.provide

        val imkbService: ImkbService =retrofit.create(ImkbService::class.java)



        var s = "Debug-infos:"
        s += "\n\n"
        s += "Device ID: ${Settings.Secure.getString(InstrumentationRegistry.getInstrumentation().targetContext.contentResolver, Settings.Secure.ANDROID_ID)}\n"
        s += "OS API Level: ${Build.VERSION.SDK_INT}\n"
        s += "platformName: ${if(Build.FINGERPRINT.contains("generic")){"AndroidSimulator"} else{"Android"}}\n"
        //s += "Device: ${Build.DEVICE}\n"
        s += "Model: ${Build.MODEL}\n"
        s += "Manufacturer: ${Build.MANUFACTURER}\n"
        s += "\n\n"

        Log.d("DENEME",s)*/

        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.omersakalli.veripark", appContext.packageName)
    }

    /*@Test
    fun deneme(){
        val AES_KEY = "u4RHlDb3+fK09ru4B5DRhn+dbxFOioFx8aDZug9lx9Y="
        val AES_IV = "uHaD40zLk9bECtDgDNI1pw=="

        val result = AES_Functions.decrypt("mEJ5oW0Zvhsp3bkVFm9GEg==",AES_KEY,AES_IV)

        print(result)
        Assert.assertEquals("aaa", result)

    }*/
}