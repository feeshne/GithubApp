package com.wentao.githubapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.wentao.githubapp.databinding.ActivityMainBinding
import com.wentao.githubapp.permission.PermissionsChecker

class MainActivity : AppCompatActivity() {

    companion object {

        const val PERMISSIONS_GRANTED = 0 // 权限授权
        const val PERMISSIONS_DENIED = 1 // 权限拒绝

        private const val PERMISSION_REQUEST_CODE = 0 // 系统权限管理页面的参数
        private const val PACKAGE_URL_SCHEME = "package:" // 方案

    }

    private lateinit var binding: ActivityMainBinding

    private var mChecker: PermissionsChecker? = null // 权限检测器
    private var isRequireCheck: Boolean = false // 是否需要系统权限检测

    // 返回传递的权限参数
    private val permissions: Array<String> = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_search, R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        mChecker = PermissionsChecker(this)
        isRequireCheck = true

    }

    override fun onResume() {
        super.onResume()
        if (isRequireCheck) {
            val permissions = permissions
            if (mChecker!!.lacksPermissions(*permissions)) {
                requestPermissions(*permissions) // 请求权限
            } else {
                allPermissionsGranted() // 全部权限都已获取
            }
        } else {
            isRequireCheck = true
        }
    }

    // 请求权限兼容低版本
    private fun requestPermissions(vararg permissions: String) {
        ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE)
    }

    // 全部权限均已获取
    private fun allPermissionsGranted() {
        setResult(PERMISSIONS_GRANTED)
    }

    /**
     * 用户权限处理,
     * 如果全部获取, 则直接过.
     * 如果权限缺失, 则提示Dialog.
     *
     * @param requestCode  请求码
     * @param permissions  权限
     * @param grantResults 结果
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST_CODE && hasAllPermissionsGranted(grantResults)) {
            isRequireCheck = true
            allPermissionsGranted()
        } else {
            isRequireCheck = false
            showMissingPermissionDialog()
        }
    }

    // 含有全部的权限
    private fun hasAllPermissionsGranted(grantResults: IntArray): Boolean {
        for (grantResult in grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                return false
            }
        }
        return true
    }

    // 显示缺失权限提示
    private fun showMissingPermissionDialog() {
        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("标题")
        builder.setMessage("权限缺失")
        // 拒绝, 退出应用
        builder.setNegativeButton("退出应用") { _, _ ->
            setResult(PERMISSIONS_DENIED)
            finish()
        }
        builder.setPositiveButton("设置") { _, _ -> startAppSettings() }
        builder.show()
    }

    // 启动应用的设置
    private fun startAppSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.data = Uri.parse(PACKAGE_URL_SCHEME + packageName)
        startActivity(intent)
    }

}