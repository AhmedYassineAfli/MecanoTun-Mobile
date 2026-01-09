package com.mecanotun.mobile

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mecanotun.mobile.databinding.ActivitySignupBinding
import com.mecanotun.mobile.viewmodel.SignUpViewModel

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var viewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[SignUpViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.navigateToLogin.observe(this) { shouldNavigate ->
            if (shouldNavigate) {
                finish()
                viewModel.onNavigatedToLogin()
            }
        }

        viewModel.errorMessage.observe(this) { error ->
            error?.let { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }
        }

        viewModel.successMessage.observe(this) { success ->
            success?.let { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }
        }

        binding.tvLogin.setOnClickListener { finish() }
    }
}

