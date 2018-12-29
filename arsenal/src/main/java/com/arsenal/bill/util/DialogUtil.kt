package com.arsenal.bill.util

import android.content.Context
import android.view.Gravity
import android.widget.ArrayAdapter
import com.arsenal.bill.R
import com.orhanobut.dialogplus.DialogPlus
import com.orhanobut.dialogplus.OnItemClickListener

  fun showListDialog(context: Context, strings: List<String>?, listener: OnItemClickListener): DialogPlus? {

    if (strings == null || strings.size < 1) return null

    val adapter = ArrayAdapter(
            context, R.layout.item_text, R.id.text_view, strings
    )

    val dialog = DialogPlus.newDialog(context)
            //                        .setHolder(new ListHolder()) // Optional, default:BasicHolder
            .setCancelable(true) // Optional default:true
            .setGravity(Gravity.CENTER) // Optional default:true
            .setAdapter(adapter) // This must be called, Any adapter can be set.
            .setOnClickListener { dialog, view -> dialog.dismiss() }
            .setOnItemClickListener(listener)
            .create()
    dialog.show()
    return dialog
}