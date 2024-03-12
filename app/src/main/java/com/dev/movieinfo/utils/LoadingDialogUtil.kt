package com.dev.movieinfo.utils

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.res.ColorStateList
import android.widget.ProgressBar
import androidx.appcompat.app.ActionBar
import com.dev.movieinfo.R

class LoadingDialogUtil:Dialog {
    companion object {
        fun show(
            context: Context
        ): LoadingDialogUtil {
            val dialog = LoadingDialogUtil(context)
            val progressBar = ProgressBar(context)
            progressBar.indeterminateTintList =
                ColorStateList.valueOf(context.resources.getColor(R.color.colorPrimary, null))
            dialog.addContentView(
                progressBar,
                ActionBar.LayoutParams(
                    ActionBar.LayoutParams.MATCH_PARENT,
                    ActionBar.LayoutParams.MATCH_PARENT
                )
            )
            dialog.show()
            return dialog
        }
    }
    constructor(context: Context):super(context, R.style.LoadingDialogStyle)
}