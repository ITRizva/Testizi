package com.example.testtaskapp.di

import android.content.Context
import com.example.testtaskapp.Data.RegisterDataRepositoryImp
import com.example.testtaskapp.Domain.interfaces.RegisterDataRepositoryDelete
import com.example.testtaskapp.Domain.interfaces.RegisterDataRepositoryGet
import com.example.testtaskapp.Domain.interfaces.RegisterDataRepositorySave
import com.example.testtaskapp.Domain.usecases.DeleteRegisterDataUseCase
import com.example.testtaskapp.Domain.usecases.GetRegisterDataUseCase
import com.example.testtaskapp.Domain.usecases.SaveRegisterDataUseCase
import dagger.Module
import dagger.Provides

import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseProvider {
    @Provides
    @Singleton
    fun provideRegisterDataRepositoryImp(@ApplicationContext context: Context):RegisterDataRepositoryImp =  RegisterDataRepositoryImp(context)
    @Provides
    @Singleton
    fun provideRegisterDataRepositoryGet(repositoryImp:RegisterDataRepositoryImp):RegisterDataRepositoryGet = repositoryImp
    @Provides
    @Singleton
    fun provideRegisterRepositorySave(repositoryImp:RegisterDataRepositoryImp):RegisterDataRepositorySave = repositoryImp
    @Singleton
    @Provides
    fun provideRegisterRepositoryDelete(repositoryImp: RegisterDataRepositoryImp):RegisterDataRepositoryDelete = repositoryImp
    @Provides
    @Singleton
    fun provideGetRegisterDataUseCase(repository:RegisterDataRepositoryGet):GetRegisterDataUseCase =  GetRegisterDataUseCase(repository)

    @Provides
    @Singleton
    fun provideSaveRegisterDataUseCase(repository:RegisterDataRepositorySave):SaveRegisterDataUseCase = SaveRegisterDataUseCase(repository)
    @Singleton
    @Provides
    fun provideDeleteRegisterDataUseCase(repository:RegisterDataRepositoryDelete):DeleteRegisterDataUseCase = DeleteRegisterDataUseCase(repository)
}