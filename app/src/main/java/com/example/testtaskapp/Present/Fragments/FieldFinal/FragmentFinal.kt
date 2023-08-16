package com.example.testtaskapp.Present.Fragments.FieldFinal

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.testtaskapp.Domain.usecases.SaveRegisterDataUseCase
import com.example.testtaskapp.Present.Fragments.FieldGRZ.FragmentGRZ
import com.example.testtaskapp.Present.Fragments.FieldVU.FragmentVU
import com.example.testtaskapp.R
import com.example.testtaskapp.databinding.FragmentFinalBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable
import javax.inject.Inject

@AndroidEntryPoint
class FragmentFinal : Fragment() {

    private val viewModel by viewModels<ViewModelFinal>()
    private var binding: FragmentFinalBinding? = null

    companion object {
        private const val FINAL_VALUE = "FINAL_VALUE"

        @JvmStatic
        fun newInstance(value: Serializable): FragmentVU {
            val transitValue: Bundle = Bundle().apply { putSerializable(FINAL_VALUE, value) }
            val fragment = FragmentVU()
            fragment.arguments = transitValue
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFinalBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.contentState.observe(viewLifecycleOwner) {
            when (it) {
                is ViewModelFinalState.Content -> {
                    binding?.textGrz?.text = it.data.grz
                    binding?.textSts?.text = it.data.sts
                    binding?.textVu?.text = it.data.vu
                }

                is ViewModelFinalState.Error -> {
                    binding?.textGrz?.text = "Не указано"
                    binding?.textSts?.text = "Не указано"
                    binding?.textVu?.text = "Не указано"
                }

                else -> {}
            }
        }
        binding?.restart?.setOnClickListener {
            viewModel.deleteData()
            viewModel.backOnFirst(requireActivity())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}