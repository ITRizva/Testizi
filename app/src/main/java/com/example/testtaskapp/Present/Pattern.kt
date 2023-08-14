package com.example.testtaskapp.Present

import android.util.Log

fun String.checkOnGroup(): Boolean {
    val patternList: ArrayList<Regex> = arrayListOf()
    val enLet = "ABEKMNOPCTYX"
    val ruLet = "АВЕКМНОРСТУХ"
    val enruLet = "$enLet$ruLet"
    patternList.apply {
        add(Regex("""[$enruLet]\d{3}[$enruLet]{2} \d{2,3}"""))
        add(Regex("""[$enruLet]{2}\d{3} \d{2,3}"""))
        add(Regex("""[$enruLet]{2}\d{4} \d{2,3}"""))
        add(Regex("""\d{4}[$enruLet]{2} \d{2,3}"""))
        add(Regex("""[$enruLet]{2}\d{2}[$enruLet]{2} \d{2,3}"""))
        add(Regex("""\d{4}[$enruLet]{2} \d{2,3}"""))
        add(Regex("""[$enruLet]{2}\d{4} \d{2,3}"""))
        add(Regex("""[$enruLet]\d{4} \d{2,3}"""))
        add(Regex("""\d{3}[$enruLet] \d{2,3}"""))
        add(Regex("""\d{4}[$enruLet] \d{2,3}"""))
    }
    patternList.forEach{ pattern->
        if(pattern.matches(this)) return true
    }
    return false
}
fun String.checkVU():Boolean{
    val ruLet= "АБВЕКМНОРСТХУЧ"
    val pattern = Regex("""\d{2}(\d{2}|[$ruLet]{2})\d{6}""")
    return pattern.matches(this)
}
fun String.checkSTS():Boolean{
    val ruLet = "АБВЕКМНОРСТХУЧ"
    val pattern = Regex("""\d{2}(\d{2}|[$ruLet]{2})\d{6}""")
    return pattern.matches(this)
}
fun String.replaceEnLetterToRu():String{
    val enLet = "ABEKMNOPCTYX"
    val ruLet = "АВЕКМНОРСТУХ"
    var newSting = this
    for(i in enLet.indices) {
        newSting = newSting.replace(enLet[i].toString(), ruLet[i].toString())
    }
    Log.d("letterString", newSting)
    return newSting
}