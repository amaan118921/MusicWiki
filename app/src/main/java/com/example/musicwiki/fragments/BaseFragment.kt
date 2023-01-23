package com.example.musicwiki.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.example.musicwiki.R
import com.example.musicwiki.activities.MainActivity
import com.example.musicwiki.helper.Constants.Companion.getFragmentClass

abstract class BaseFragment : Fragment(), View.OnClickListener {
    abstract fun getLayoutRes(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutRes(), container, false)
    }

    private fun getSupportFragmentManager(): FragmentManager {
        return (requireActivity() as MainActivity).supportFragmentManager
    }

    fun addFragment(fragmentID: String, bundle: Bundle?) {
        getSupportFragmentManager().commit {
            add(R.id.container, getFragmentClass(fragmentID), bundle)
            addToBackStack(fragmentID)
            setReorderingAllowed(true)
        }
    }

    fun finish() {
        requireActivity().finish()
    }

    fun popBackStack() {
        getSupportFragmentManager().popBackStack()
    }


}