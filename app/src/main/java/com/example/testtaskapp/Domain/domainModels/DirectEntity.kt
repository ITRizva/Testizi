package com.example.testtaskapp.Domain.domainModels

import java.io.Serializable

data class AutoRegisterEntity(
    val grz:String? = null,
    val sts:String?= null,
    val vu:String? = null
): Serializable