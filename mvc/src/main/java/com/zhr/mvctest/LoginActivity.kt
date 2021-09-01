package com.zhr.mvctest

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

/**
 * https://www.bilibili.com/video/BV1Dk4y1C7mm?p=1
 * mvc架构：实现功能: 登录及登陆状态提示
 *
 * kotlin几个语法糖：
 * by lazy
 * object:  设置监听回调常用
 * companion object  方便类.调用
 * JVMStatic
 * in 遍历
 * is 包含
 * Class::class.java
 * .let{}   合并同一变量
 * 构造方法这里的，变量名：数据类型（数据类型指的不是var和val而是String等的数据类型）
 */
class LoginActivity : AppCompatActivity(), LoginModel.DoLoginCallback {

    private val loginModel by lazy {
        LoginModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initListener()
    }

    private fun initListener() {
        btnLogin.setOnClickListener {
            toLogin()
        }
    }

    /**
     * 点击登录
     * -> 判断帐号密码为空情况
     * -> 判断账号是否可用情况 -> 帐号可用，判断密码是否正确，然后登陆
     */
    private fun toLogin() {
        val username = etUsername.text.toString().trim()
        val password = etPassword.text.toString().trim()
        if (TextUtils.isEmpty(username)) {
            btnLogin.text = "帐号不能为空"
            clear()
            return
        }
        if (TextUtils.isEmpty(password)) {
            btnLogin.text = "密码不能为空"
            clear()
            return
        }
        // 点击之后，先不让再点击
        btnLogin.isEnabled = false
        // 需要联网操作，就在model层进行
        loginModel.checkUsername(username, object : LoginModel.CheckUsernameCallback {
            override fun usernameCanUse() {
                btnLogin.text = "用户名可用"
                loginModel.doLogin(username, password, this@LoginActivity)
            }

            override fun usernameCannotUse() {
                btnLogin.text = "用户名不可用"
                btnLogin.isEnabled = true
                clear()
            }
        })
    }

    private fun clear() {
        etUsername.setText("")
        etPassword.setText("")
        etUsername.requestFocus()
    }

    override fun loading() {
        btnLogin.text = "登录中"
    }

    override fun loginSuccess() {
        btnLogin.text = "密码正确，登录成功"
        btnLogin.isEnabled = true
        clear()
    }

    override fun loginFail() {
        btnLogin.text = "密码错误，登录失败"
        btnLogin.isEnabled = true
        clear()
    }
}