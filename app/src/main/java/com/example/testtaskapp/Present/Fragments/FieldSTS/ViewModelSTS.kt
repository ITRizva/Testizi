package com.example.testtaskapp.Present.Fragments.FieldSTS

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.testtaskapp.Domain.domainModels.AutoRegisterEntity
import com.example.testtaskapp.Domain.usecases.GetRegisterDataUseCase
import com.example.testtaskapp.Present.Fragments.Dialogs.AlertDialogTask
import com.example.testtaskapp.Present.Fragments.FieldVU.FragmentVU
import com.example.testtaskapp.Present.checkSTS
import com.example.testtaskapp.R
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.Serializable
import javax.inject.Inject

@HiltViewModel
class ViewModelSTS @Inject constructor(private val savedStateHandle: SavedStateHandle,private val getRegisterDataUseCase: GetRegisterDataUseCase): ViewModel() {

    private val _contentArguments = MutableLiveData<AutoRegisterEntity>()
    val contentArguments: LiveData<AutoRegisterEntity> =  _contentArguments

    fun loadContent(){
        val arguments = savedStateHandle.get<Serializable>(FragmentSTS.STS_VALUE) as AutoRegisterEntity
        _contentArguments.value = arguments
    }

    fun setStsData(sts:String){
        _contentArguments.value = _contentArguments.value?.copy(sts = sts)
    }

    fun launchNextFragment(activity: FragmentActivity){
        val entity = _contentArguments.value as AutoRegisterEntity
        if(entity.sts?.checkSTS() == true){
            val fragmentInstance = FragmentVU.newInstance(entity)
            activity.supportFragmentManager.beginTransaction().replace(R.id.mainContainer, fragmentInstance).addToBackStack("VU").commit()
        }else{
            Toast.makeText(activity, "Проверьте правильность ввода", Toast.LENGTH_SHORT).show()
        }
    }
    fun showSkipDialog(activity: FragmentActivity){
        AlertDialogTask().show(activity.supportFragmentManager,"SKIP")
    }

}