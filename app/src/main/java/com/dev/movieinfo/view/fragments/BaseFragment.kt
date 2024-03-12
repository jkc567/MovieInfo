package com.dev.movieinfo.view.fragments

import androidx.fragment.app.Fragment
import com.dev.movieinfo.utils.LoadingDialogUtil

open class BaseFragment:Fragment() {
    var diag: LoadingDialogUtil?=null
    fun showLoading()
    {
        if(diag==null)
        {
            diag=LoadingDialogUtil.show(requireContext())
        }
        else
        {
            diag?.show()
        }
    }
    fun hideLoading()
    {
        diag?.hide()
    }
}