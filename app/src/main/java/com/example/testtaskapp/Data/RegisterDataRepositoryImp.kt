package com.example.testtaskapp.Data


import android.content.Context
import com.example.testtaskapp.Data.localModels.LocalEntity
import com.example.testtaskapp.Domain.interfaces.RegisterDataRepositoryGet
import com.example.testtaskapp.Domain.interfaces.RegisterDataRepositorySave
import com.example.testtaskapp.Domain.domainModels.DirectEntity
import com.example.testtaskapp.Domain.interfaces.RegisterDataRepositoryDelete
import com.google.gson.Gson

class RegisterDataRepositoryImp(private val context: Context): RegisterDataRepositoryGet,
    RegisterDataRepositorySave,RegisterDataRepositoryDelete {
    private val sharedPreferences = context.getSharedPreferences(SHARED_REGISTER_NAME,Context.MODE_PRIVATE)
    override fun saveRegisterData(data: LocalEntity): Boolean {
        val refData = Gson().toJson(data.toDirectEntity())
        sharedPreferences.edit().putString(KEY_REGISTER_DATA,refData).apply()
        return true
    }

    override fun getRegisterData(): DirectEntity? {
        val stringData = sharedPreferences.getString(KEY_REGISTER_DATA, "")
        return Gson().fromJson(stringData, DirectEntity::class.java)
    }
    override fun deleteRegisterData() {
        val sharedPreferences = context.getSharedPreferences(SHARED_REGISTER_NAME, Context.MODE_PRIVATE)
        sharedPreferences?.edit()?.remove(KEY_REGISTER_DATA)?.apply()
    }
    companion object {
        private const val SHARED_REGISTER_NAME = "shared_register_name"
        private const val KEY_REGISTER_DATA = "register_data"
    }




}