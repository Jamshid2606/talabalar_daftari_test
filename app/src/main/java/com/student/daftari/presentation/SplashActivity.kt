package com.student.daftari.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.facebook.FacebookSdk
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.student.daftari.R
import com.student.daftari.app.App.Companion.activity
import com.student.daftari.data.local.shp.AppReference
import com.student.daftari.databinding.ActivitySplashBinding
import com.student.daftari.presentation.auth.AuthActivity
import com.student.daftari.presentation.main.activity.MainActivity
import com.student.daftari.utils.LocalData.googleSignInClient
import com.student.daftari.utils.enums.ScreenEnum
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private val shared by lazy {
        AppReference(this)
    }
    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FacebookSdk.sdkInitialize(this);
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        activity = this
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAGS_CHANGED
        )
        binding.icon.alpha = 0f
        binding.icon.animate().setDuration(2500).alpha(1f).withEndAction {
            when (shared.currentScreen) {
                ScreenEnum.LOGIN -> {
                    val intent = Intent(this, AuthActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    finish()
                }
                ScreenEnum.HOME -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    finish()
                }
            }


        }
    }
}