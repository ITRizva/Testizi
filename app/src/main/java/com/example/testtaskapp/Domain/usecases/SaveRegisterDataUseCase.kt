package com.example.testtaskapp.Domain.usecases

import com.example.testtaskapp.Data.localModels.LocalEntity
import com.example.testtaskapp.Data.toDirectEntity
import com.example.testtaskapp.Data.toLocalEntity
import com.example.testtaskapp.Domain.domainModels.DirectEntity
import com.example.testtaskapp.Domain.interfaces.RegisterDataRepositoryGet
import com.example.testtaskapp.Domain.interfaces.RegisterDataRepositorySave
import javax.inject.Inject

class SaveRegisterDataUseCase @Inject constructor(private val repository: RegisterDataRepositorySave) {
    fun execute(entity: DirectEntity){
        repository.saveRegisterData(data = entity.toLocalEntity())
    }
}