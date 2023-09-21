package com.example.idnow_test

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant
import io.flutter.embedding.android.FlutterFragmentActivity


class MainActivity : FlutterFragmentActivity() {
    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)
        var splashScreen: SplashScreen? = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            splashScreen = installSplashScreen();
        }

        // Aligns the Flutter view vertically with the window.
        WindowCompat.setDecorFitsSystemWindows(window, false)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            splashScreen?.setOnExitAnimationListener { splashScreenView -> splashScreenView.remove() }
        }
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine)
        window.addFlags(WindowManager.LayoutParams.FLAG_SECURE)

        MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            "customChannel"
        ).setMethodCallHandler { call, result ->
            if (call.method == "startNewActivity") {
                val intent =Intent(this.applicationContext, FirstActivity::class.java)
                intent.flags = FLAG_ACTIVITY_NEW_TASK
                this.applicationContext.startActivity(intent)
                result.success("LAUNCHED")
            }
        }
    }
}
