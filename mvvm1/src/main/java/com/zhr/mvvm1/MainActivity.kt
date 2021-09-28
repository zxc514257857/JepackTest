package com.zhr.mvvm1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * https://www.bilibili.com/video/BV1xk4y1y7HR
 * mvvm + jetpack  视图v + 数据m + 逻辑vm 分离
 * 面向网络 -> 数据来自服务器    viewModel + liveData + lifecycle + dataBinding
 * 面向数据库 -> 数据来自sqlite3   room + paging（分页加载） + livedata
 * app架构设计：层次化、模块化、控件化（自定义View）、组件化
 * 一个app 5-10个module是正常的  module模块  component组件
 * 模块化（module）是组件化（compopent）和插件化（plug）的基础
 * 模块化和组件化、插件化的理解
 * 组件化偏向代码层的复用、模块化偏向业务层的分层、插件化就是没有启动图标的app，给另一个app提供功能
 * （插件化也需要用到组件化和模块化功能）
 *
 * ViewBinding和DataBinding的区别？
 * ViewBinding只是找到View，DataBinding绑定数据和视图是双向的
 *
 * 本地构建仓库 -> maven 私服搭建 : Nexus Repositories Manager
 * 构建仓库：nexus、artifactory pro（JFrog Artifactory）
 * 代码仓库：git
 * 搭建本地构建仓库，并代理到阿里云。可以使用本地仓库，也可以代理远程仓库
 * 不仅可以android，还可以ios，php，python，go等语言提供帮助
 * 使用JFrog Artifactory
 * 替换了几个地方的仓库配置： Project的build.gradle中的仓库配置以及 gradlewrapper的地址配置
 * 我创建了一个本地仓库，有创建了几个代理的远程仓库，然后把这几个仓库放到建立的虚拟仓库里面，把这个虚拟仓库引用进项目即可。可以加快构建速度，在几秒内构建完
 * maven{
 *      url 'http://localhost:8081/artifactory/android-group'  // 自己建立的虚拟仓库
 * }
 *
 * Windows系统下的包管理工具：https://chocolatey.org/install
 * WindowsPowerShell 和cmd的区别：WindowsPowerShell 是高级版的命令行工具
 * WindowsPowelShell安装 chocolatey： Get-ExecutionPolicy -> Set-ExecutionPolicy AllSigned  FQ了但一直安装不上（在家里的Win10系统上安装成功）
 * https://community.chocolatey.org/packages   这是Chocolatey的安装包仓库
 * Scoop 也安装不了  回去用自己的笔记本电脑试一下
 * $psversiontable.psversion.major 查看PowerShell 版本号
 *
 * git下拉代码在指定位置  git clone xxx.git e:/demo/   如果不指定位置则在命令行程序所在的路径
 *
 * Retrofit 的默认Client不打印日志 所以我们自己配置Okhttp的日志拦截器
 *
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}