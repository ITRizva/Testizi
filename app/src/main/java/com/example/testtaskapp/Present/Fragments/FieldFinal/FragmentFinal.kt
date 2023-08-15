package com.example.testtaskapp.Present.Fragments.FieldFinal

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.testtaskapp.Data.RegisterDataRepositoryImp
import com.example.testtaskapp.Domain.domainModels.DirectEntity
import com.example.testtaskapp.Domain.usecases.SaveRegisterDataUseCase
import com.example.testtaskapp.Present.Fragments.FieldGRZ.FragmentGRZ
import com.example.testtaskapp.Present.Fragments.FieldVU.FragmentVU
import com.example.testtaskapp.Present.replaceEnLetterToRu
import com.example.testtaskapp.R
import com.example.testtaskapp.databinding.FragmentFinalBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable
import javax.inject.Inject

@Suppress("DEPRECATION")
@AndroidEntryPoint
class FragmentFinal:Fragment() {
    @Inject
    lateinit var saveRegisterData:SaveRegisterDataUseCase
    private lateinit var binding:FragmentFinalBinding
    private var transitData = DirectEntity()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable(FINAL_VALUE, DirectEntity::class.java)
        } else {
            val refData = arguments?.getSerializable(FINAL_VALUE) as? DirectEntity
            if (refData != null) transitData = refData
        }
        binding = FragmentFinalBinding.inflate(inflater, container, false)
        binding.textGrz.text = transitData.grz?.replaceEnLetterToRu() ?: "Не указано"
        binding.textSts.text = transitData.sts ?: "Не указано"
        binding.textVu.text = transitData.vu ?: "Не указано"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(transitData.grz != null || transitData.vu != null){
            saveRegisterData.execute(transitData)
        }
        binding.restart.setOnClickListener {//этого здесь не должно(нет в требованиях - для моего удобства) быть просто костыл для бастрой проверки
            val sharedPreferences = context?.getSharedPreferences(SHARED_REGISTER_NAME, Context.MODE_PRIVATE)//этого здесь не должно(нет в требованиях - для моего удобства) быть просто костыл для бастрой проверки
            sharedPreferences?.edit()?.remove(KEY_REGISTER_DATA)?.apply()//этого здесь не должно(нет в требованиях - для моего удобства) быть просто костыл для бастрой проверки
            parentFragmentManager.beginTransaction().replace(R.id.mainContainer,FragmentGRZ()).addToBackStack("REFRESH").commit()//этого здесь не должно(нет в требованиях - для моего удобства) быть просто костыл для бастрой проверки
        }
    }

    companion object{
        private const val FINAL_VALUE = "FINAL_VALUE"
        @JvmStatic
        fun newInstance(value: Serializable): FragmentFinal {
            val transitValue: Bundle = Bundle().apply { putSerializable(FINAL_VALUE,value) }
            val fragment = FragmentFinal()
            fragment.arguments = transitValue
            return fragment
        }
        private const val SHARED_REGISTER_NAME = "shared_register_name" //этого здесь не должно(нет в требованиях - для моего удобства) быть просто костыл для бастрой проверки
        private const val KEY_REGISTER_DATA = "register_data"               //этого здесь не должно(нет в требованиях - для моего удобства) быть просто костыл для бастрой проверки
    }

}