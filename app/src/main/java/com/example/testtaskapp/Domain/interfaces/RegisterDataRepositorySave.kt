package com.example.testtaskapp.Domain.interfaces

import com.example.testtaskapp.Data.localModels.LocalEntity
import com.example.testtaskapp.Domain.domainModels.DirectEntity

interface RegisterDataRepositorySave {
    fun saveRegisterData(data: LocalEntity):Boolean
}