package com.example.testtaskapp.Domain.usecases

import com.example.testtaskapp.Domain.domainModels.DirectEntity
import com.example.testtaskapp.Domain.interfaces.RegisterDataRepositoryGet
import javax.inject.Inject

class GetRegisterDataUseCase  @Inject constructor(private val repository:RegisterDataRepositoryGet) {

    fun execute():DirectEntity?{
        return repository.getRegisterData()
    }

}