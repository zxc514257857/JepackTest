package com.example.databinding2.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.LogUtils
import com.example.databinding2.R
import com.example.databinding2.databinding.ActivityStock1Binding
import com.example.databinding2.viewmodel.Stock1ViewModel
import com.example.databinding2.widget.NumberInputFilter

/**
 * 使用databindging方式实现
 * 通过databinding 进行数据和UI的双向绑定，之前的例子实现的都是数据变了更新UI，即单向绑定
 *
 *
 * adb 命令学习：
 * adb devices 查看当前设备
 * adb remount 重新挂载
 * adb reboot 设备重启
 * adb shell reboot -p 设备关机
 * adb -s xxx(设备名称) xxx(执行的命令)  选择设备
 * adb shell 进入android shell
 * adb pull xxx(内部文件地址) xxx(外部文件目录) 拉出(文件、文件夹均可)
 * adb push xxx(外部文件目录) xxx(内部文件地址) 推入(文件、文件夹均可)
 * adb logcat > log.txt 开始抓取log，到电脑c盘对应文件夹中，点击ctrl+c 停止抓取log
 * (搜索异常：Exception、at + 包名、cause by)
 * 在logcat框中过滤： adb shell ->
 * logcat | grep START （主要是看ActivityManager为标题的内容输出）
 * 获取pkg（package）、获取cat（category）、获取act（activity）、获取flg（flag）以及cmp（component）
 * adb shell logcat 在命令行窗口看log
 * adb install xxx(apk全路径名称)  安装apk  -r 替换安装
 * adb uninstall xxx（apk包名）  卸载apk
 * adb shell am start -n xxx(component)  启动apk
 * adb shell boardcast -a 手动模拟发送广播
 * adb shell screencap -p /sdcard/screen.png -> adb pull /sdcard/screen.png ./  adb截图
 * adb shell input keyevent xxx(在android.view.KeyEvent 这个目录下找按键码)  adb手动模拟点击按键
 * 参考文档：https://developer.android.google.cn/studio/command-line/adb?hl=zh_cn
 * 参考视频：https://www.bilibili.com/video/BV11Z4y1s73D?p=9
 *
 * 常用shell命令：
 * ls -l 以列表方式查看文件、cd 进入(cd .. 返回)、pwd 查看文件路径、rm -r(文件夹)f(强制) xxx(文件夹名称) 删除
 * touch xxx(文件名) 创建文件、mkdir xxx(文件夹名) 创建文件夹
 * drwx（d表示文件、r表示权限可读、w表示权限可写、x表示权限可执行）
 * mv xxx(文件名 myfile) xxx(文件路径 my/my1)、su 获取最高权限、cat xxx(文件名) 查看文件内容
 *
 * */
class Stock1Activity : AppCompatActivity() {

    private lateinit var viewModel: Stock1ViewModel
    private lateinit var binding: ActivityStock1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView<ActivityStock1Binding>(this, R.layout.activity_stock1)
        // 如果要实现双向绑定，需要binding设置lifeCycleOwner
        binding.lifecycleOwner = this
        // 限制EditText只能输入小数点后两位的神器
        binding.etCurPriceCentent.filters = arrayOf(NumberInputFilter())

        viewModel = ViewModelProvider(this).get(Stock1ViewModel::class.java)
        // xml里面的dataBinding变量都需要绑定哦
        binding.viewModel = viewModel
        binding.eventHandler = EventHandler()

        // 数据在监听UI变化 ：：数据和视图双向绑定
        viewModel.apply {
            price.observe(this@Stock1Activity, Observer {
                LogUtils.i("price:::$it")
                viewModel.updateTotalValue()
            })
            num.observe(this@Stock1Activity, Observer {
                LogUtils.i("num:::$it")
                viewModel.updateTotalValue()
            })
            agreement.observe(this@Stock1Activity, Observer {
                LogUtils.i("agreement:::$it")
            })
            price1.observe(this@Stock1Activity, Observer {
                LogUtils.i("price:::$it")
                viewModel.updateTotalValue1()
            })
            num1.observe(this@Stock1Activity, Observer {
                LogUtils.i("num:::$it")
                viewModel.updateTotalValue1()
            })
        }
    }

    // 数据变化通知UI变化 ：：数据和视图双向绑定   双向绑定的实质是观察者模式
    inner class EventHandler {
        fun onStockBtnClick() {
            viewModel.price.value = "999.99"
        }
    }
}