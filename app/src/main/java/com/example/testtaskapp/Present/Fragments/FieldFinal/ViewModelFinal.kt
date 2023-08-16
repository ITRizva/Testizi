package com.example.testtaskapp.Present.Fragments.FieldFinal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtaskapp.Domain.domainModels.AutoRegisterEntity

import com.example.testtaskapp.Domain.usecases.DeleteRegisterDataUseCase
import com.example.testtaskapp.Domain.usecases.GetRegisterDataUseCase
import com.example.testtaskapp.Domain.usecases.SaveRegisterDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


sealed class ViewModelFinalState() {
    object Initial : ViewModelFinalState()
    object Loading : ViewModelFinalState()
    data class Content(val data: AutoRegisterEntity) : ViewModelFinalState()
    object Error : ViewModelFinalState()
}

@HiltViewModel
class ViewModelFinal @Inject constructor(
    private val deleteRegisterDataUseCase: DeleteRegisterDataUseCase,
    private val getRegisterDataUseCase: GetRegisterDataUseCase
) : ViewModel() {

    private val _contentState: MutableLiveData<ViewModelFinalState> = MutableLiveData<ViewModelFinalState>(ViewModelFinalState.Initial)
    val contentState: LiveData<ViewModelFinalState> = _contentState

    fun loadData() {
        _contentState.value = ViewModelFinalState.Loading
        val data = getRegisterDataUseCase.execute()
        if (data != null) {
            _contentState.value = ViewModelFinalState.Content(data)
        } else {
            _contentState.value = ViewModelFinalState.Error
        }
    }


}