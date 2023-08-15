package com.example.testtaskapp.Present.Fragments.FieldFinal

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.testtaskapp.Data.RegisterDataRepositoryImp
import com.example.testtaskapp.Domain.domainModels.DirectEntity
import com.example.testtaskapp.Domain.usecases.SaveRegisterDataUseCase
import com.example.testtaskapp.Present.Fragments.FieldGRZ.FragmentGRZ
import com.example.testtaskapp.Present.Fragments.FieldGRZ.ViewModelGRZ
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
    private val viewModel by viewModels<ViewModelFinal>()
    private  var binding:FragmentFinalBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.showData()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.contentState.observe(viewLifecycleOwner){
            when(it){
                is ViewModelFinalState.Content ->{
                    binding?.textGrz?.text = it.data.grz
                    binding?.textSts?.text = it.data.sts
                    binding?.textVu?.text = it.data.vu
                }
                is ViewModelFinalState.Error ->{
                    binding?.textGrz?.text = "Не указано"
                    binding?.textSts?.text = "Не указано"
                    binding?.textVu?.text = "Не указано"
                }
                else -> {}
            }
        }
        binding?.restart?.setOnClickListener {

            parentFragmentManager.beginTransaction().replace(R.id.mainContainer,FragmentGRZ()).addToBackStack("REFRESH").commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}