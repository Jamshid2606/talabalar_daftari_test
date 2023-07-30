package com.student.daftari.presentation.auth.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.material.tabs.TabLayoutMediator
import com.student.daftari.R
import com.student.daftari.app.App.Companion.instance
import com.student.daftari.data.local.shp.AppReference
import com.student.daftari.data.model.User
import com.student.daftari.databinding.FragmentAuthBinding
import com.student.daftari.presentation.auth.adapters.ViewPagerAdapter
import com.student.daftari.presentation.auth.viewmodels.imp.AuthViewModelImp
import com.student.daftari.presentation.main.activity.MainActivity
import com.student.daftari.utils.LocalData.REQUEST_CODE
import com.student.daftari.utils.LocalData.googleSignInClient
import com.student.daftari.utils.enums.ScreenEnum
import com.student.daftari.utils.showToast
import com.student.daftari.utils.state
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_auth.*
import java.util.*

@AndroidEntryPoint
class AuthFragment : Fragment(R.layout.fragment_auth) {
    private val viewModel by viewModels<AuthViewModelImp>()
    private val shared by lazy { AppReference(instance) }
    private var phone: String = ""
    private var name: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.errorLiveData.observe(this, errorObserver)
        viewModel.successLiveData.observe(this) {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
            shared.currentScreen = ScreenEnum.HOME
        }
        viewModel.accountLiveData.observe(this) {
            println("Tushdi")
            findNavController().navigate(R.id.confirmFragment)
        }
        viewModel.progressLiveData.observe(this, progressObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            initTab()
            googleL.setOnClickListener {
                googleSignInClient!!.signInIntent.also {
                    startActivityForResult(it, REQUEST_CODE)
                }
            }
            facebookL.setOnClickListener {
                initLoginWithFacebook()
            }
            twitterL.setOnClickListener {
                viewModel.loginTwitter()
            }
    }

    private fun initLoginWithFacebook() {
        val loginManager = LoginManager.getInstance()
        val callbackManager = CallbackManager.Factory.create()

        loginManager
            .registerCallback(
                callbackManager,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess(result: LoginResult) {
                        viewModel.loginFaceBook(result.accessToken)
                    }

                    override fun onCancel() {
                    }

                    override fun onError(error: FacebookException) {
                        showToast(error.message.toString())
                    }
                })
        loginManager.logInWithReadPermissions(
            requireActivity(),
            listOf(
                "email",
                "public_profile",
                "user_birthday"
            )
        );
    }

    private fun initTab() {
        val adapter = ViewPagerAdapter(this@AuthFragment)
        viewPager.adapter = adapter
        val pagesList = arrayListOf<String>()
        pagesList.add("Kirish")
        pagesList.add("Ro`yhatdan O`tish")
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = pagesList[position]
        }.attach()
        adapter.setRegisterBtnClickListener { s, s2 ->
            phone = s2
            println("Tushdo")
            name = s
            viewModel.loginWithPhoneName(s2, s)
        }

    }

    private fun googleAuthWithFirebase(authGoogleSignInAccount: GoogleSignInAccount) {
        viewModel.googleSign(authGoogleSignInAccount)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            val account = GoogleSignIn.getSignedInAccountFromIntent(data).result
            account.apply {
                googleAuthWithFirebase(this)
            }
        }
    }

    private val errorObserver = Observer<String> {
        showToast(it)
    }

    private val progressObserver = Observer<Boolean> {
        state(it)
    }

    private val successObserver = Observer<String> {
        val data = User()
        println("tushdi")
        data.phoneNumber = "" + phone
        data.name = name.toString()
        val bundle = Bundle()
        bundle.putSerializable("data", data)
        bundle.putString("uid", it)
        findNavController().navigate(R.id.confirmFragment, bundle)
    }
}