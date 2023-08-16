package com.example.testtaskapp.Present.Fragments.FieldVU

import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.testtaskapp.Domain.domainModels.AutoRegisterEntity
import com.example.testtaskapp.Domain.usecases.SaveRegisterDataUseCase
import com.example.testtaskapp.Present.Fragments.Dialogs.AlertDialogTask
import com.example.testtaskapp.Present.Fragments.FieldFinal.FragmentFinal
import com.example.testtaskapp.Present.Fragments.FieldSTS.FragmentSTS
import com.example.testtaskapp.Present.checkSTS
import com.example.testtaskapp.Present.checkVU
import com.example.testtaskapp.R
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.Serializable
import javax.inject.Inject

@HiltViewModel
class ViewModelVU @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val saveRegisterDataUseCase: SaveRegisterDataUseCase
) : ViewModel() {

    private val _contentArguments = MutableLiveData(AutoRegisterEntity())
    val contentArguments: LiveData<AutoRegisterEntity?> = _contentArguments

    fun loadContent() {
        val arguments = savedStateHandle.get<Serializable>(FragmentVU.VU_VALUE) as? AutoRegisterEntity ?: AutoRegisterEntity()
        _contentArguments.value = arguments
    }

    fun setVuData(vu: String) {
        _contentArguments.value = _contentArguments.value?.copy(vu = vu)
    }

    fun launchNextFragment(activity: FragmentActivity) {
        val entity = _contentArguments.value as AutoRegisterEntity
        saveRegisterDataUseCase.execute(entity)
        if (entity.vu?.checkVU() == true) {
            activity.supportFragmentManager.beginTransaction().replace(R.id.mainContainer, FragmentFinal()).addToBackStack("FINAL").commit()
        } else {
            Toast.makeText(activity, "Проверьте правильность ввода", Toast.LENGTH_SHORT).show()
        }
    }

    fun showSkipDialog(activity: FragmentActivity) {
        val entity = _contentArguments.value as AutoRegisterEntity
        if (entity.grz != null || entity.vu != null) {
            saveRegisterDataUseCase.execute(entity)
        }
        val dialog = AlertDialogTask()
        dialog.isFinal = true
        dialog.show(activity.supportFragmentManager, "SKIP")
    }


}