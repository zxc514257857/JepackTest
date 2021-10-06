package com.example.navigation.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.navigation.R
import com.example.navigation.base.BaseFragment
import com.example.navigation.databinding.FragmentAvatarBinding

class AvatarFragment : BaseFragment<FragmentAvatarBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.btnToLogin?.setOnClickListener {
            findNavController().navigate(R.id.to_fg_login)
        }
    }
}