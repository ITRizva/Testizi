package com.example.testtaskapp.Domain.interfaces

import com.example.testtaskapp.Domain.domainModels.DirectEntity

interface RegisterDataRepositorySave {
    fun saveRegisterData(data: DirectEntity):Boolean
}