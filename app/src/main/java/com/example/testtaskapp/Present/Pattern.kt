package com.example.testtaskapp.Present

fun String.checkOnGroup():Boolean{
      val enLet = "ABEKMNOPCTYX"
      val ruLet = "АВЕКМНОРСТУХ"
      val enruLet = "$enLet$ruLet"
      val pattern1 = Regex("""[$enruLet]\d\d\d[$enruLet][$enruLet]\d\d""")
      return pattern1.matches(this)


}