package ru.spider.imgurclient.view.fragments

import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.appbar.MaterialToolbar
import ru.spider.imgurclient.R

open class BaseFragment : Fragment() {
    var toolBar: MaterialToolbar? = null

    protected fun setToolbar(@StringRes title: Int, withBackButton: Boolean, view: View) {
        toolBar = view.findViewById(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolBar)
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.setTitle(title)
        if (withBackButton) {
            actionBar?.setDisplayHomeAsUpEnabled(true)
            actionBar?.setDisplayShowHomeEnabled(true)
            toolBar?.setNavigationOnClickListener { view.findNavController().navigateUp() }
            actionBar?.setHomeAsUpIndicator(ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_arrow_back_24))
        }
    }

    override fun onDestroy() {
        toolBar?.setNavigationOnClickListener(null)
        super.onDestroy()
    }
}