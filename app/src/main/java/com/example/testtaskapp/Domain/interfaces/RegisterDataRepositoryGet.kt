package com.example.testtaskapp.Domain.interfaces

import com.example.testtaskapp.Domain.domainModels.AutoRegisterEntity


interface RegisterDataRepositoryGet {
    fun getRegisterData(): AutoRegisterEntity?
}
