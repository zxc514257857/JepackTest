package com.example.navigation.activity

import android.os.Bundle
import com.example.navigation.R
import com.example.navigation.base.BaseActivity
import com.example.navigation.databinding.ActivityAgreementBinding

class AgreementActivity : BaseActivity<ActivityAgreementBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setBackgroundDrawableResource(R.color.transparent)
        // fragment中获取bundle是从arguement中获取
        // activity中获取bundle是从intent中获取
        binding?.tv2?.text = intent.getStringExtra("toAgreement")
    }
}