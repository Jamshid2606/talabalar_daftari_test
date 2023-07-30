package com.student.daftari.presentation.auth

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.play.core.integrity.IntegrityTokenRequest
import com.student.daftari.R
import com.student.daftari.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


@AndroidEntryPoint
open class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        IntegrityTokenRequest.builder()
            .setCloudProjectNumber(551503664846)
            .setNonce("PcUMIw8Te6viCTFw-1fAtGgPzKUo-dKc01TG_kxHyZo")
            .build()
        printKeyHash()
    }
    private fun printKeyHash() {
        try {
            val info: PackageInfo = packageManager.getPackageInfo("com.student.daftari", PackageManager.GET_SIGNATURES)
            for (signature in info.signatures){
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.e("KEYHASH", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        }catch (e: PackageManager.NameNotFoundException){

        }catch (e: NoSuchAlgorithmException){

        }
    }

}