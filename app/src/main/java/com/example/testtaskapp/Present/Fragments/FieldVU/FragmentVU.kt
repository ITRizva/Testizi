package com.example.testtaskapp.Present.Fragments.FieldVU

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.testtaskapp.Present.Fragments.FieldGRZ.FragmentGRZ
import java.io.Serializable

class FragmentVU:Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    companion object{
        private const val VU_VALUE = "VU_VALUE"
        @JvmStatic
        fun newInstance(value: Serializable): FragmentGRZ {
            val transitValue: Bundle = Bundle().apply { putSerializable(VU_VALUE,value) }
            val fragment = FragmentGRZ()
            fragment.arguments = transitValue
            return fragment
        }
    }
}