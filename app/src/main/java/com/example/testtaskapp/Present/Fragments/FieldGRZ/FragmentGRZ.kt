package com.example.testtaskapp.Present.Fragments.FieldGRZ

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.testtaskapp.Domain.domainModels.DirectEntity
import com.example.testtaskapp.Domain.usecases.GetRegisterDataUseCase
import com.example.testtaskapp.Present.Fragments.Dialogs.AlertDialogTask
import com.example.testtaskapp.Present.Fragments.FieldFinal.FragmentFinal
import com.example.testtaskapp.Present.Fragments.FieldSTS.FragmentSTS
import com.example.testtaskapp.Present.checkOnGroup
import com.example.testtaskapp.R
import com.example.testtaskapp.databinding.AlertDialogBinding
import com.example.testtaskapp.databinding.FragmentGrzBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FragmentGRZ:Fragment() {


    private lateinit var binding: FragmentGrzBinding
    private val viewModel by viewModels<ViewModelGRZ>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.checkWay()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGrzBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.continueButton.setOnClickListener {
            val region:String = binding.editRegion.text.toString()
            val result:String = (binding.editSeriaNumber.text.toString()+" "+ region).uppercase()
            if(result.checkOnGroup() && region.length>1){
                val transit = DirectEntity(
                    grz = result
                )
                val fragmentInstance = FragmentSTS.newInstance(transit)
                parentFragmentManager.beginTransaction().add(R.id.mainContainer,fragmentInstance).addToBackStack("STS").commit()
            }
            else{
                Toast.makeText(requireActivity(),"Проверьте правильность ввода",Toast.LENGTH_SHORT).show()
            }

        }
        binding.skipButton.setOnClickListener {
            val fragment = AlertDialogTask()
            fragment.show(parentFragmentManager,"Dialog")
        }
        viewModel.contentState.observe(viewLifecycleOwner){
                    showScreen(it)
        }
    }
    private fun showScreen(state:ViewModelGRZState){
        when(state){
            is ViewModelGRZState.Content -> {
                val fragmentInstance = FragmentFinal.newInstance(state.data)
                parentFragmentManager.beginTransaction().replace(R.id.mainContainer,fragmentInstance).commit()
            }
            else -> {}
        }

    }



}