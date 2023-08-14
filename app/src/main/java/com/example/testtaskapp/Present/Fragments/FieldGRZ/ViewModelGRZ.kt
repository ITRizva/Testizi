package com.example.testtaskapp.Present.Fragments.FieldGRZ

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtaskapp.Domain.domainModels.DirectEntity
import com.example.testtaskapp.Domain.interfaces.RegisterDataRepositoryGet
import com.example.testtaskapp.Domain.usecases.GetRegisterDataUseCase
import com.example.testtaskapp.Domain.usecases.SaveRegisterDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ViewModelGRZ @Inject constructor(private val getRegisterDataUseCase: GetRegisterDataUseCase) : ViewModel() {

    private val _contentState: MutableLiveData<ViewModelGRZState> =
        MutableLiveData<ViewModelGRZState>(ViewModelGRZState.Initial)
    val contentState: LiveData<ViewModelGRZState> = _contentState
    fun checkWay() {
        val state = _contentState.value
        when {
            state is ViewModelGRZState.Initial -> loadContent()
            state is ViewModelGRZState.Content -> loadContent()
            state is ViewModelGRZState.Void -> loadContent()
            else -> return
        }
    }

    private fun loadContent() {
        _contentState.value = ViewModelGRZState.Loading
        val result = getRegisterDataUseCase.execute()
        if (result == null) {
            _contentState.value = ViewModelGRZState.Void
            return
        }
        _contentState.value = ViewModelGRZState.Content(result)

    }
}
sealed class ViewModelGRZState {
    object Initial : ViewModelGRZState()
    object Loading : ViewModelGRZState()
    data class Content(val data: DirectEntity) : ViewModelGRZState()
    object Void : ViewModelGRZState()
}