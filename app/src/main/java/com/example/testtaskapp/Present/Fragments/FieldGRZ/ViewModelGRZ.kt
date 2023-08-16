package com.example.testtaskapp.Present.Fragments.FieldGRZ

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtaskapp.Domain.domainModels.AutoRegisterEntity
import com.example.testtaskapp.Domain.interfaces.RegisterDataRepositoryGet
import com.example.testtaskapp.Domain.usecases.GetRegisterDataUseCase
import com.example.testtaskapp.Domain.usecases.SaveRegisterDataUseCase
import com.example.testtaskapp.Present.Fragments.Dialogs.AlertDialogTask
import com.example.testtaskapp.Present.Fragments.FieldFinal.FragmentFinal
import com.example.testtaskapp.Present.Fragments.FieldSTS.FragmentSTS
import com.example.testtaskapp.Present.checkOnGroup
import com.example.testtaskapp.Present.replaceEnLetterToRu
import com.example.testtaskapp.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ViewModelGRZ @Inject constructor(private val getRegisterDataUseCase: GetRegisterDataUseCase) : ViewModel() {

    private val _content: MutableLiveData<ViewModelGRZState> = MutableLiveData<ViewModelGRZState>(ViewModelGRZState.Initial)
    val content: LiveData<ViewModelGRZState> = _content

    private var numberAuto = ""
    private var regionAuto = ""

    fun loadContent(activity:FragmentActivity) {
        _content.value = ViewModelGRZState.Loading
        val result = getRegisterDataUseCase.execute()
        if (result != null) {
            activity.supportFragmentManager.beginTransaction().replace(R.id.mainContainer,FragmentFinal()).commit()
        } else {
            _content.value = ViewModelGRZState.Error
        }
    }

    fun launchNextFragment(activity: FragmentActivity) {
        val entity = _content.value as ViewModelGRZState.Content
        val dataGrz = entity.data.grz?.uppercase()
        if (dataGrz?.checkOnGroup() == true) {
            val fragmentInstance = FragmentSTS.newInstance(entity.data)
            activity.supportFragmentManager.beginTransaction().replace(R.id.mainContainer, fragmentInstance).addToBackStack("STS").commit()
        } else {
            Toast.makeText(activity, "Проверьте правильность ввода", Toast.LENGTH_SHORT).show()
        }
    }

    fun showSkipDialog(activity: FragmentActivity){
        AlertDialogTask().show(activity.supportFragmentManager,"SKIP")
    }

    fun setAutoNumber(number: String) {
        numberAuto = number
        concatAutoGRZ()
    }

    fun setAutoRegion(region: String) {
        regionAuto = region
        concatAutoGRZ()
    }

    private fun concatAutoGRZ() {
        val concatGRZ = "$numberAuto $regionAuto"
        _content.value = ViewModelGRZState.Content(AutoRegisterEntity(grz = concatGRZ))
    }
}

sealed class ViewModelGRZState {
    object Initial : ViewModelGRZState()
    object Loading : ViewModelGRZState()
    data class Content(val data: AutoRegisterEntity) : ViewModelGRZState()
    object Error : ViewModelGRZState()
}