package com.zhr.mvc

class LoginModel {

    fun checkUsername(username: String, checkUsernameCallback: CheckUsernameCallback) {
        if (username.contains("0")) {
            checkUsernameCallback.usernameCannotUse()
        } else {
            checkUsernameCallback.usernameCanUse()
        }
    }

    interface CheckUsernameCallback {
        fun usernameCanUse()
        fun usernameCannotUse()
    }

    fun doLogin(username: String, password: String, doLoginCallback: DoLoginCallback) {
        doLoginCallback.loading()
        if (username == "12345" && password == "12345") {
            doLoginCallback.loginSuccess()
        } else {
            doLoginCallback.loginFail()
        }
    }

    interface DoLoginCallback {
        fun loading()
        fun loginSuccess()
        fun loginFail()
    }
}