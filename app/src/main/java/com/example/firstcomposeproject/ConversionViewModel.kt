package com.example.firstcomposeproject

import android.util.Log
import androidx.lifecycle.ViewModel

class ConversionViewModel : ViewModel() {
    private var _fahrenheit = 0
    val fahrenheit
        get() = _fahrenheit

    private var _celsius = 0.0
    val celsius
        get() = _celsius

    private var _celsiusText = ""
    val celsiusText
        get() = _celsiusText

    private var _emailText = ""
    val emailText
        get() = _emailText

    private var _passText = ""
    val passText
        get() = _passText


    fun onCalculate(input: Int) {
        if (input in 0..160) {
            _fahrenheit = input
            calculateCelsius(_fahrenheit!!)
        } else
            _fahrenheit = 0
    }

    private fun calculateCelsius(fh: Int) {
        var cs: Double = (fh.toDouble() - 32) * 5 / 9
        _celsius = String.format("%.1f", cs).toDouble()

        val csText = String.format("%.1f", cs)
        val lastChar = csText[csText.length - 1]

        _celsiusText = if (lastChar.digitToInt() == 0) {
            cs.toInt().toString()
        } else {
            String.format("%.1f", cs)
        }
    }

    fun loginApiCall(email: String, pass: String) {
        _emailText = email
        _passText = pass

        Log.e("loginApiCall","EmailText="+_emailText +"  PassText="+_passText)
    }

    fun getListOfArray(): ArrayList<String> {
        val arrayList = ArrayList<String>()//Creating an empty arraylist
        arrayList.add("item 1")//Adding object in arraylist
        arrayList.add("item 2")
        arrayList.add("item 3")
        arrayList.add("item 4")
        arrayList.add("item 5")
        arrayList.add("item 6")
        arrayList.add("item 7")
        arrayList.add("item 8")
        arrayList.add("item 9")
        arrayList.add("item 10")
        arrayList.add("item 11")
        arrayList.add("item 12")
        arrayList.add("item 13")
        arrayList.add("item 14")
        arrayList.add("item 15")
        arrayList.add("item 16")
        return arrayList
    }
}