package com.example.testtaskapp.Present.Fragments.FieldGRZ

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.testtaskapp.Domain.usecases.GetRegisterDataUseCase
import com.example.testtaskapp.databinding.FragmentGrzBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class FragmentGRZ  : Fragment() {

    private var binding: FragmentGrzBinding? = null
    private val viewModel by viewModels<ViewModelGRZ>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadContent()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGrzBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.editSeriaNumber?.addTextChangedListener {
            viewModel.setAutoNumber(it.toString())
        }
        binding?.editRegion?.addTextChangedListener {
            viewModel.setAutoRegion(it.toString())
        }
        binding?.continueButton?.setOnClickListener {
            viewModel.launchNextFragment(requireActivity())
        }
        binding?.skipButton?.setOnClickListener {
            viewModel.showSkipDialog(requireActivity())
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}