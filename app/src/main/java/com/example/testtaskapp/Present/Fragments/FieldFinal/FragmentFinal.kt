package com.example.testtaskapp.Present.Fragments.FieldFinal

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.testtaskapp.Domain.usecases.SaveRegisterDataUseCase
import com.example.testtaskapp.Present.Fragments.FieldGRZ.FragmentGRZ
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable
import javax.inject.Inject

@AndroidEntryPoint
class FragmentFinal:Fragment() {
    @Inject
    lateinit var saveRegisterData:SaveRegisterDataUseCase

    companion object{
        private const val FINAL_VALUE = "FINAL_VALUE"
        @JvmStatic
        fun newInstance(value: Serializable): FragmentGRZ {
            val transitValue: Bundle = Bundle().apply { putSerializable(FINAL_VALUE,value) }
            val fragment = FragmentGRZ()
            fragment.arguments = transitValue
            return fragment
        }
    }
}