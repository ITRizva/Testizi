package com.example.testtaskapp.Present.Fragments.FieldSTS

import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract.Directory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.testtaskapp.Domain.domainModels.AutoRegisterEntity

import com.example.testtaskapp.Present.Fragments.Dialogs.AlertDialogTask
import com.example.testtaskapp.Present.Fragments.FieldGRZ.FragmentGRZ
import com.example.testtaskapp.Present.Fragments.FieldGRZ.ViewModelGRZ
import com.example.testtaskapp.Present.Fragments.FieldVU.FragmentVU
import com.example.testtaskapp.Present.checkSTS
import com.example.testtaskapp.Present.replaceEnLetterToRu
import com.example.testtaskapp.R
import com.example.testtaskapp.databinding.FragmentGrzBinding
import com.example.testtaskapp.databinding.FragmentStsBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable

@Suppress("DEPRECATION")
@AndroidEntryPoint
class FragmentSTS: Fragment() {

    private  var binding:FragmentStsBinding? = null
    private val viewModel by viewModels<ViewModelSTS>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadContent()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStsBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.contentArguments.observe(viewLifecycleOwner){
            binding?.textGrz?.text = it.grz?.uppercase()
        }
        binding?.editSts?.addTextChangedListener {
            viewModel.setStsData(it.toString())
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
        const val STS_VALUE = "STS_VALUE"
        @JvmStatic
        fun newInstance(value: Serializable): FragmentSTS {
            val transitValue: Bundle = Bundle().apply { putSerializable(STS_VALUE,value) }
            val fragment = FragmentSTS()
            fragment.arguments = transitValue
            return fragment
        }
    }
}