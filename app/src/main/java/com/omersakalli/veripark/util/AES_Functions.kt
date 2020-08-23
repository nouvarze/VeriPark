package com.omersakalli.veripark.util

import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object AES_Functions {

    /**
     * Encrypts a plain text string with AES encryption
     * @param period Gets an plain string to encrypt
     * @param aesKey Base64 encoded string as AES key
     * @param aesIV Base64 encoded string as AES initialization vector
     */
    fun encrypt(period: String, aesKey: String, aesIV: String): String {
        val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
        cipher.init(
            Cipher.ENCRYPT_MODE,
            SecretKeySpec(Base64.decode(aesKey.toByteArray(), Base64.DEFAULT), "AES"),
            IvParameterSpec(Base64.decode(aesIV.toByteArray(), Base64.DEFAULT))
        )
        val encryptedPeriod = cipher.doFinal(period.toByteArray())
        return Base64.encodeToString(encryptedPeriod, Base64.DEFAULT)
    }

    fun decrypt(symbol:String,aesKey: String, aesIV: String):String{
        val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
        cipher.init(
            Cipher.DECRYPT_MODE,
            SecretKeySpec(Base64.decode(aesKey.toByteArray(), Base64.DEFAULT), "AES"),
            IvParameterSpec(Base64.decode(aesIV.toByteArray(), Base64.DEFAULT))
        )
        val decryptedSymbol = cipher.doFinal(Base64.decode(symbol.toByteArray(),Base64.DEFAULT))
        return decryptedSymbol.decodeToString()
    }
}