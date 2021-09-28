package com.example.navigation.fragment

import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import androidx.annotation.RequiresApi
import com.example.navigation.R
import com.example.navigation.base.BaseFragment
import com.example.navigation.databinding.FragmentSettingBinding

class SettingFragment : BaseFragment<FragmentSettingBinding>() {

    @RequiresApi(Build.VERSION_CODES.KITKAT)  // 4.4以上
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedElementEnterTransition =
            TransitionInflater.from(requireContext()).inflateTransition(R.transition.shared_img)
        viewBinding?.tv2?.text = arguments?.getString("toSetting")
    }
}