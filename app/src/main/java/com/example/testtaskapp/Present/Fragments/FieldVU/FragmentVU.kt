package com.example.testtaskapp.Present.Fragments.FieldVU

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel

import com.example.testtaskapp.Present.Fragments.Dialogs.AlertDialogTask
import com.example.testtaskapp.Present.Fragments.FieldFinal.FragmentFinal
import com.example.testtaskapp.Present.Fragments.FieldGRZ.FragmentGRZ
import com.example.testtaskapp.Present.Fragments.FieldSTS.FragmentSTS
import com.example.testtaskapp.Present.Fragments.FieldSTS.ViewModelSTS
import com.example.testtaskapp.Present.checkVU
import com.example.testtaskapp.Present.replaceEnLetterToRu
import com.example.testtaskapp.R
import com.example.testtaskapp.databinding.FragmentStsBinding
import com.example.testtaskapp.databinding.FragmentVuBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable

@Suppress("DEPRECATION")
@AndroidEntryPoint
class FragmentVU:Fragment() {
    private  var binding: FragmentVuBinding? = null
    private val viewModel by viewModels<ViewModelVU>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadContent()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVuBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.contentArguments.observe(viewLifecycleOwner){
            binding?.textGrz?.text = it?.grz.toString()
            binding?.textSts?.text = it?.sts.toString()
        }
        binding?.editVuField?.addTextChangedListener {
            viewModel.setVuData(it.toString())
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
    companion object{
        const val VU_VALUE = "VU_VALUE"
        @JvmStatic
        fun newInstance(value: Serializable): FragmentVU {
            val transitValue: Bundle = Bundle().apply { putSerializable(VU_VALUE,value) }
            val fragment = FragmentVU()
            fragment.arguments = transitValue
            return fragment
        }
    }
}