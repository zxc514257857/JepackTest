package com.zhr.mvp2.lifecycle

/**
 * 通过注册这个接口获取到 LifecycleProvider
 * 直接让Base层实现，然后返回 LifecycleProvider即可
 */
interface ILifecycleOwner {

    fun getLifecycleProvider(): LifecycleProvider
}