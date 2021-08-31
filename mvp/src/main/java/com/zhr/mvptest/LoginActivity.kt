package com.zhr.mvptest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

/**
 * mvp架构：实现功能，登录及登陆状态提示
 * 之前在c层（activity）进行逻辑处理，现在在v层（activity）不进行逻辑处理，只进行调用和回调，逻辑处理在p层（presenter）进行
 */
class LoginActivity : AppCompatActivity(), LoginPresenter.LoginCallback {

    private val loginPresenter by lazy{
        LoginPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnLogin.setOnClickListener {
            toLogin()
        }
    }

    // 在v层就是处理视图，注册p层的回调监听，处理回调监听
    private fun toLogin() {
        val username = etUsername.text.toString().trim()
        val password = etPassword.text.toString().trim()
        loginPresenter.doLogin(username, password, this)
    }

    private fun clear() {
        etUsername.setText("")
        etPassword.setText("")
        etUsername.requestFocus()
    }

    override fun usernameEmpty() {
        btnLogin.text = "帐号不能为空"
        clear()
    }

    override fun passwordEmpty() {
        btnLogin.text = "密码不能为空"
        clear()
    }

    override fun startLogin() {
        btnLogin.text = "开始登陆"
        btnLogin.isEnabled = false
    }

    override fun usernameCannotUse() {
        btnLogin.text = "用户名不可用"
        btnLogin.isEnabled = true
        clear()
    }

    override fun usernameCanUse() {
        btnLogin.text = "用户名可用"
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
        btnLogin.text = "密码错误，登陆失败"
        btnLogin.isEnabled = true
        clear()
    }
}