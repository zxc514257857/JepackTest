package com.example.navigation.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.navigation.R
import com.example.navigation.base.BaseFragment
import com.example.navigation.databinding.FragmentRegisterBinding

class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

    companion object {
        private const val TAG: String = "RegisterFragment"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding?.btnToAvatar?.setOnClickListener {
            findNavController().navigate(R.id.to_fg_avatar)
        }
        viewBinding?.tv?.text = arguments?.getString("toRegister")
    }
}