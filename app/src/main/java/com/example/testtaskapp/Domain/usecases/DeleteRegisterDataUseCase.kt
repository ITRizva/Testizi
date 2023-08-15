package com.example.testtaskapp.Domain.usecases

import com.example.testtaskapp.Domain.interfaces.RegisterDataRepositoryDelete
import com.example.testtaskapp.Domain.interfaces.RegisterDataRepositoryGet
import javax.inject.Inject

class DeleteRegisterDataUseCase @Inject constructor(private val repository: RegisterDataRepositoryDelete) {
    fun execute(){
        repository.deleteRegisterData()
    }
}