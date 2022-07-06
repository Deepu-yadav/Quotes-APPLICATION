package com.example.quotes

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import java.nio.charset.Charset

class MainViewModel(val cotext:Context) : ViewModel() {
    private var quoteList:Array<Quote> = emptyArray()
    private var index=0;
    init {
        quoteList=loadQuoteFromAssest()
    }

    private fun loadQuoteFromAssest(): Array<Quote> {
        val inputStream=cotext.assets.open("quotes.json")
        val size:Int=inputStream.available()
        val buffer=ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json= String(buffer, Charsets.UTF_8)  // it will convert the byte array into string
        val gson=Gson()
        return gson.fromJson(json, Array<Quote>::class.java)

    }
    fun getQuote() = quoteList[index]
    fun nextQuote() = quoteList[++index]
    fun previousQuotes() = quoteList[--index]
}