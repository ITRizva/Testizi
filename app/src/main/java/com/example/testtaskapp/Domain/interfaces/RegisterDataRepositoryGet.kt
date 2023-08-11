package com.example.testtaskapp.Domain.interfaces

import com.example.testtaskapp.Domain.domainModels.DirectEntity

interface RegisterDataRepositoryGet {
    fun getRegisterData(): DirectEntity?
}
