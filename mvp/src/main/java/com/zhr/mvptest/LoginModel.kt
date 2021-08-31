package com.zhr.mvptest

class LoginModel {

    // m层回调给p层
    fun checkUsername(username: String, checkUsernameCallback: CheckUsernameCallback) {
        if (username.contains("0")) {
            checkUsernameCallback.usernameCannotUse()
        } else {
            checkUsernameCallback.usernameCanUse()
        }
    }

    interface CheckUsernameCallback {
        fun usernameCannotUse()
        fun usernameCanUse()
    }

    // m层回调给p层
    fun checkUsernamePassword(
        username: String,
        password: String,
        checkUsernamePasswordCallback: CheckUsernamePasswordCallback,
    ) {
        checkUsernamePasswordCallback.loading()
        if (username == "12345" && password == "12345") {
            checkUsernamePasswordCallback.loginSuccess()
        } else {
            checkUsernamePasswordCallback.loginFail()
        }
    }

    interface CheckUsernamePasswordCallback {
        fun loading()
        fun loginSuccess()
        fun loginFail()
    }
}