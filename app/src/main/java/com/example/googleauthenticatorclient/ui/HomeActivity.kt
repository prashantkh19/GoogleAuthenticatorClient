package com.example.googleauthenticatorclient.ui

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.googleauthenticatorclient.R
import com.example.googleauthenticatorclient.data.Repository
import com.example.googleauthenticatorclient.di.DaggerHomeActivityComponent
import com.example.googleauthenticatorclient.di.RepositoryModule
//import com.example.googleauthenticatorclient.di.DaggerMainActivityComponent
import com.example.googleauthenticatorclient.ui.viewModel.HomeActivityViewModel
import com.example.googleauthenticatorclient.ui.viewModel.HomeActivityViewModelFactory
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject


class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var repository :Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val component = DaggerHomeActivityComponent.builder()
                .repositoryModule(RepositoryModule(this))
                .build()
        component.init(this)

        val homeActivityViewModelFactory = HomeActivityViewModelFactory(repository)
        val viewModel = ViewModelProviders.of(this,homeActivityViewModelFactory).get(HomeActivityViewModel::class.java)

        //moving to activity
        if (viewModel.isKeySet()) {
            startActivity(Intent(this@HomeActivity, NewAccountActivity::class.java))
        }

        //new account listener
        tv_new_account.setOnClickListener {
            startActivity(Intent(this@HomeActivity, NewAccountActivity::class.java))
        }

        tv_status.text = when(viewModel.getStatus()){
            true -> getString(R.string.verified)
            false -> getString(R.string.failed)
            else -> getString(R.string.status_placeholder)
        }

        //submit button listener
        bt_submit.setOnClickListener {
            try {
                viewModel.verifyCode(et_input_code.text.toString().toInt())
                tv_status.text = when(viewModel.getStatus()){
                    true -> getString(R.string.verified)
                    false -> getString(R.string.failed)
                    else -> getString(R.string.status_placeholder)
                }
            } catch (e: Exception) {
                Toast.makeText(this@HomeActivity, getString(R.string.enter_valid_code), Toast.LENGTH_SHORT).show()
            }
        }
    }
}
