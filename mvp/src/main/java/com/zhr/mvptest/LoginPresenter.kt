package com.zhr.mvptest

import android.text.TextUtils

/**
 * 在v层注册回调，p层回调给v层；同时在p层注册回调，p层回调给m层
 */
class LoginPresenter : LoginModel.CheckUsernameCallback {

    private var loginCallback: LoginCallback? = null
    private var username: String? = null
    private var password: String? = null

    private val loginModel by lazy {
        LoginModel()
    }

    // p层回调给v层
    fun doLogin(username: String, password: String, loginCallback: LoginCallback) {
        this.loginCallback = loginCallback
        this.username = username
        this.password = password
        if (TextUtils.isEmpty(username)) {
            // 告知view层用户名为空
            loginCallback.usernameEmpty()
            return
        }
        if (TextUtils.isEmpty(password)) {
            // 告知view层密码为空
            loginCallback.passwordEmpty()
            return
        }
        // 需要联网操作，就在model层进行
        loginCallback.startLogin()
        loginModel.checkUsername(username, this)
    }

    interface LoginCallback {
        fun usernameEmpty()
        fun passwordEmpty()
        fun startLogin()
        fun usernameCannotUse()
        fun usernameCanUse()
        fun loading()
        fun loginSuccess()
        fun loginFail()
    }

    override fun usernameCannotUse() {
        loginCallback?.usernameCannotUse()
    }

    override fun usernameCanUse() {
        loginCallback?.usernameCanUse()
        loginModel.checkUsernamePassword(username!!, password!!, object: LoginModel.CheckUsernamePasswordCallback{
            override fun loading() {
                loginCallback?.loading()
            }

            override fun loginSuccess() {
                loginCallback?.loginSuccess()
            }

            override fun loginFail() {
                loginCallback?.loginFail()
            }
        })
    }
}