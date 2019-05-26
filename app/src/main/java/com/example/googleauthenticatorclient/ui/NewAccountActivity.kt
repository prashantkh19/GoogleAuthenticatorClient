package com.example.googleauthenticatorclient.ui

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.example.googleauthenticatorclient.R
import com.example.googleauthenticatorclient.data.Repository
import com.example.googleauthenticatorclient.di.DaggerHomeActivityComponent
import com.example.googleauthenticatorclient.di.RepositoryModule
import com.example.googleauthenticatorclient.ui.viewModel.NewAccountActivityViewModel
import com.example.googleauthenticatorclient.ui.viewModel.NewAccountActivityViewModelFactory
import kotlinx.android.synthetic.main.activity_new_account.*
import javax.inject.Inject

class NewAccountActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_account)

        val component = DaggerHomeActivityComponent.builder()
                .repositoryModule(RepositoryModule(this))
                .build()
        component.init(this)

        val newAccountActivityViewModelFactory = NewAccountActivityViewModelFactory(repository)
        val viewModel = ViewModelProviders.of(this,newAccountActivityViewModelFactory ).get(NewAccountActivityViewModel::class.java)

        btn_submit.setOnClickListener { View.OnClickListener {
            val code = et_input_code.text.toString()
            try {
                if(viewModel.verifyCode(code.toInt())){
                    viewModel.setSharedKey(code)
                    startActivity(Intent(this@NewAccountActivity,HomeActivity::class.java))
                    Toast.makeText(this@NewAccountActivity, getString(R.string.register_successfully), Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this@NewAccountActivity, getString(R.string.incorrect_code), Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@NewAccountActivity, getString(R.string.enter_valid_code), Toast.LENGTH_SHORT).show()
            }
        } }
    }
}
