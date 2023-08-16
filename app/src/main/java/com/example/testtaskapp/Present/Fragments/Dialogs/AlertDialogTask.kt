package com.example.testtaskapp.Present.Fragments.Dialogs

import android.annotation.SuppressLint
import android.app.ActionBar
import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.testtaskapp.Domain.domainModels.AutoRegisterEntity
import com.example.testtaskapp.Present.Fragments.FieldFinal.FragmentFinal
import com.example.testtaskapp.Present.Fragments.FieldVU.FragmentVU
import com.example.testtaskapp.R
import com.example.testtaskapp.databinding.AlertDialogBinding
import com.example.testtaskapp.databinding.FragmentFinalBinding

class AlertDialogTask : DialogFragment() {

    private var binding: AlertDialogBinding? = null

    var isFinal = false

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = AlertDialogBinding.inflate(layoutInflater)
        binding?.TextLocationDialog?.text = "Вы уверены что хотите пропустить данный шаг?"
        binding?.CancelButtonLocation?.text = "Нет"
        binding?.AccessButtonLocation?.text = "Да"
        binding?.AccessButtonLocation?.setOnClickListener {
            onClickEvent(it)
        }
        binding?.CancelButtonLocation?.setOnClickListener {
            onClickEvent(it)
        }
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding?.root)
        val dialog = builder.create()
        dialog.window!!.setGravity(Gravity.BOTTOM)
        dialog.window!!.setBackgroundDrawable(resources.getDrawable(R.drawable.item_round, null))
        dialog.window!!.attributes.width = ActionBar.LayoutParams.MATCH_PARENT
        dialog.show()
        return dialog
    }

    private fun onClickEvent(view: View) {
        when (view) {
            binding?.CancelButtonLocation -> {
                dismiss()
            }

            binding?.AccessButtonLocation -> {
                if (isFinal) {
                    parentFragmentManager.beginTransaction().add(R.id.mainContainer, FragmentFinal()).addToBackStack("FINAL").commit()
                } else {
                    parentFragmentManager.beginTransaction().add(R.id.mainContainer, FragmentVU()).addToBackStack("VU").commit()
                }
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}