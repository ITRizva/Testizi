package com.example.testtaskapp.Data


import android.content.Context
import com.example.testtaskapp.Domain.domainModels.AutoRegisterEntity
import com.example.testtaskapp.Domain.interfaces.RegisterDataRepositoryGet
import com.example.testtaskapp.Domain.interfaces.RegisterDataRepositorySave
import com.example.testtaskapp.Domain.interfaces.RegisterDataRepositoryDelete
import com.google.gson.Gson
import javax.inject.Inject

class RegisterDataRepositoryImp @Inject constructor(context: Context) : RegisterDataRepositoryGet, RegisterDataRepositorySave, RegisterDataRepositoryDelete {

    private val sharedPreferences = context.getSharedPreferences(SHARED_REGISTER_NAME, Context.MODE_PRIVATE)
    private val gson = Gson()

    override fun saveRegisterData(data: AutoRegisterEntity) {
        sharedPreferences.edit().putString(KEY_REGISTER_DATA, gson.toJson(data)).apply()
    }

    override fun getRegisterData(): AutoRegisterEntity? {
        val stringData = sharedPreferences.getString(KEY_REGISTER_DATA, "")
        return gson.fromJson(stringData, AutoRegisterEntity::class.java)
    }

    override fun deleteRegisterData() {
        sharedPreferences?.edit()?.remove(KEY_REGISTER_DATA)?.apply()
    }

    companion object {
        private const val SHARED_REGISTER_NAME = "shared_register_name"
        private const val KEY_REGISTER_DATA = "register_data"
    }


}