package com.example.testtaskapp.Present.Fragments.FieldSTS

import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract.Directory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.testtaskapp.Domain.domainModels.DirectEntity
import com.example.testtaskapp.Present.Fragments.Dialogs.AlertDialogTask
import com.example.testtaskapp.Present.Fragments.FieldGRZ.FragmentGRZ
import com.example.testtaskapp.Present.Fragments.FieldVU.FragmentVU
import com.example.testtaskapp.Present.checkSTS
import com.example.testtaskapp.Present.replaceEnLetterToRu
import com.example.testtaskapp.R
import com.example.testtaskapp.databinding.FragmentGrzBinding
import com.example.testtaskapp.databinding.FragmentStsBinding
import java.io.Serializable

@Suppress("DEPRECATION")
class FragmentSTS: Fragment() {
    private  var binding:FragmentStsBinding? = null
    private var transitData= DirectEntity()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable(STS_VALUE,DirectEntity::class.java)
        } else{
            val refData = arguments?.getSerializable(STS_VALUE) as? DirectEntity
            if(refData != null) transitData = refData
        }
        binding = FragmentStsBinding.inflate(inflater,container,false)
        binding?.textGrz?.text = transitData.grz?.replaceEnLetterToRu()
        return binding?.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.continueButton?.setOnClickListener {
            val result = binding?.editSts?.text.toString().uppercase()
            if(result.checkSTS()){
                val transit = DirectEntity(grz = transitData.grz,sts=result)
                val fragmentInstance = FragmentVU.newInstance(transit)
                parentFragmentManager.beginTransaction().replace(R.id.mainContainer,fragmentInstance).addToBackStack("VU").commit()
            }
            else{
                Toast.makeText(requireActivity(),"Проверьте правильность ввода", Toast.LENGTH_SHORT).show()
            }
        }
        binding?.skipButton?.setOnClickListener {
            val fragment = AlertDialogTask() as DialogFragment
            fragment.show(parentFragmentManager,"Dialog")
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
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