package com.omersakalli.veripark.util


import java.text.SimpleDateFormat
import java.util.*


object DateCheck {
    /**
     * returns true if lifetime still has time
     * @param lifetimeString string value of last valid datetime
     */
    fun isLifetimeValid(lifetimeString: String):Boolean{
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
        val currentTime:Date = Calendar.getInstance().time
        val lifetime = sdf.parse(lifetimeString)
        return currentTime.before(lifetime)
    }
}