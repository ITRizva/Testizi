package com.example.testtaskapp.Present.Fragments.FieldSTS

import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract.Directory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.testtaskapp.Domain.domainModels.DirectEntity
import com.example.testtaskapp.Present.Fragments.FieldGRZ.FragmentGRZ
import com.example.testtaskapp.databinding.FragmentGrzBinding
import com.example.testtaskapp.databinding.FragmentStsBinding
import java.io.Serializable

@Suppress("DEPRECATION")
class FragmentSTS: Fragment() {
    private lateinit var binding:FragmentStsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var transitData= DirectEntity()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable(STS_VALUE,DirectEntity::class.java)
        } else{
            val refData = arguments?.getSerializable(STS_VALUE) as? DirectEntity
            if(refData != null) transitData = refData
        }
        binding = FragmentStsBinding.inflate(inflater,container,false)
        binding.textGrz.text = transitData.grz
        return binding.root


    }
    companion object{
        private const val STS_VALUE = "STS_VALUE"
        @JvmStatic
        fun newInstance(value: Serializable): FragmentSTS {
            val transitValue: Bundle = Bundle().apply { putSerializable(STS_VALUE,value) }
            val fragment = FragmentSTS()
            fragment.arguments = transitValue
            return fragment
        }
    }
}