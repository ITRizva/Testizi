package com.example.testtaskapp.Present.Fragments.FieldVU

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.testtaskapp.Domain.domainModels.DirectEntity
import com.example.testtaskapp.Present.Fragments.FieldGRZ.FragmentGRZ
import com.example.testtaskapp.Present.Fragments.FieldSTS.FragmentSTS
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
        binding.textGrz.text = transitData.grz
        binding.textSts.text = transitData.sts
        return binding.root
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