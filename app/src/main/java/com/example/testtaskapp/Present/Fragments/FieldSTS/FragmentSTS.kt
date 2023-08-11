package com.example.testtaskapp.Present.Fragments.FieldSTS

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.testtaskapp.Present.Fragments.FieldGRZ.FragmentGRZ
import java.io.Serializable

class FragmentSTS: Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    companion object{
        private const val STS_VALUE = "STS_VALUE"
        @JvmStatic
        fun newInstance(value: Serializable): FragmentGRZ {
            val transitValue: Bundle = Bundle().apply { putSerializable(STS_VALUE,value) }
            val fragment = FragmentGRZ()
            fragment.arguments = transitValue
            return fragment
        }
    }
}