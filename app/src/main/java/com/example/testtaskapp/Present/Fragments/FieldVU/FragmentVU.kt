package com.example.testtaskapp.Present.Fragments.FieldVU

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.testtaskapp.Domain.domainModels.DirectEntity
import com.example.testtaskapp.Present.Fragments.Dialogs.AlertDialogTask
import com.example.testtaskapp.Present.Fragments.FieldFinal.FragmentFinal
import com.example.testtaskapp.Present.Fragments.FieldGRZ.FragmentGRZ
import com.example.testtaskapp.Present.Fragments.FieldSTS.FragmentSTS
import com.example.testtaskapp.Present.checkVU
import com.example.testtaskapp.Present.replaceEnLetterToRu
import com.example.testtaskapp.R
import com.example.testtaskapp.databinding.FragmentStsBinding
import com.example.testtaskapp.databinding.FragmentVuBinding
import java.io.Serializable

@Suppress("DEPRECATION")
class FragmentVU:Fragment() {
    lateinit var binding: FragmentVuBinding
    private var transitData = DirectEntity()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable(VU_VALUE,DirectEntity::class.java)
        } else{
            val refData = arguments?.getSerializable(VU_VALUE) as? DirectEntity
            if(refData != null) transitData = refData
        }
        binding = FragmentVuBinding.inflate(inflater,container,false)
        binding.textGrz.text = transitData.grz?.replaceEnLetterToRu()
        binding.textSts.text = transitData.sts
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.continueButton.setOnClickListener {
            val result = binding.editVuField.text.toString().uppercase()
            if(result.checkVU()){
                val transit = DirectEntity(grz = transitData.grz,sts = transitData.sts,vu = result)
                val fragmentInstance = FragmentFinal.newInstance(transit)
                parentFragmentManager.beginTransaction().add(R.id.mainContainer,fragmentInstance).addToBackStack("FINAL").commit()

            }
            else{
                Toast.makeText(requireActivity(),"Проверьте правильность ввода", Toast.LENGTH_SHORT).show()
            }
        }
        binding.skipButton.setOnClickListener {
            val fragment = AlertDialogTask()
            fragment.isFinal = transitData
            fragment.show(parentFragmentManager,"Dialog")
        }
    }
    companion object{
        private const val VU_VALUE = "VU_VALUE"
        @JvmStatic
        fun newInstance(value: Serializable): FragmentVU {
            val transitValue: Bundle = Bundle().apply { putSerializable(VU_VALUE,value) }
            val fragment = FragmentVU()
            fragment.arguments = transitValue
            return fragment
        }
    }
}