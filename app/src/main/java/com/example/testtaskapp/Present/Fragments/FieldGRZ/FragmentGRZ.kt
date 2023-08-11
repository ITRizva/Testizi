package com.example.testtaskapp.Present.Fragments.FieldGRZ

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.testtaskapp.Domain.usecases.GetRegisterDataUseCase
import com.example.testtaskapp.databinding.FragmentGrzBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FragmentGRZ:Fragment() {
    @Inject
    lateinit var getRegisterData:GetRegisterDataUseCase
    private lateinit var binding: FragmentGrzBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGrzBinding.inflate(inflater,container,false)
        return binding.root
    }


}