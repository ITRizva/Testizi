package com.example.testtaskapp.Domain.usecases

import com.example.testtaskapp.Domain.domainModels.AutoRegisterEntity
import com.example.testtaskapp.Domain.interfaces.RegisterDataRepositorySave
import javax.inject.Inject

class SaveRegisterDataUseCase @Inject constructor(private val repository: RegisterDataRepositorySave) {
    fun execute(entity: AutoRegisterEntity){
        repository.saveRegisterData(data = entity)
    }
}