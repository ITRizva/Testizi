package com.example.testtaskapp.Domain.usecases

import com.example.testtaskapp.Domain.domainModels.AutoRegisterEntity
import com.example.testtaskapp.Domain.interfaces.RegisterDataRepositoryGet
import javax.inject.Inject

class GetRegisterDataUseCase  @Inject constructor(private val repository:RegisterDataRepositoryGet) {

    fun execute():AutoRegisterEntity?{
        return repository.getRegisterData()
    }

}