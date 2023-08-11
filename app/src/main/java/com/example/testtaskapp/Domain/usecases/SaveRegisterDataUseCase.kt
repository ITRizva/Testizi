package com.example.testtaskapp.Domain.usecases

import com.example.testtaskapp.Data.localModels.LocalEntity
import com.example.testtaskapp.Domain.interfaces.RegisterDataRepositoryGet
import com.example.testtaskapp.Domain.interfaces.RegisterDataRepositorySave
import javax.inject.Inject

class SaveRegisterDataUseCase @Inject constructor(repository: RegisterDataRepositorySave) {
    fun execute(entity: LocalEntity){

    }
}