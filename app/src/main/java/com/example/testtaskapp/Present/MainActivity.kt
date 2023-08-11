package com.example.testtaskapp.Present

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testtaskapp.Present.Fragments.FieldGRZ.FragmentGRZ
import com.example.testtaskapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction().addToBackStack("first").add(R.id.mainContainer,FragmentGRZ()).commit()
        }
    }
}

