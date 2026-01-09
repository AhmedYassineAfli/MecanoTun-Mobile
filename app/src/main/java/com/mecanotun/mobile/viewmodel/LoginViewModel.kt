package com.mecanotun.mobile.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mecanotun.mobile.api.CustomerDto
import com.mecanotun.mobile.api.RetrofitClient
import com.mecanotun.mobile.repository.CustomerRepository
import com.mecanotun.mobile.utils.Result
import com.mecanotun.mobile.utils.SharedPreferencesManager
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val prefsManager = SharedPreferencesManager(application)
    private val customerRepository = CustomerRepository(RetrofitClient.instance)

    val email = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    private val _loginSuccess = MutableLiveData<CustomerDto?>()
    val loginSuccess: LiveData<CustomerDto?> = _loginSuccess

    private val _navigateToHome = MutableLiveData<Boolean>(false)
    val navigateToHome: LiveData<Boolean> = _navigateToHome

    init {

        if (prefsManager.isLoggedIn()) {
            _navigateToHome.value = true
        }
    }

    fun login() {
        val emailValue = email.value?.trim() ?: ""
        val passwordValue = password.value?.trim() ?: ""

        if (emailValue.isEmpty() || passwordValue.isEmpty()) {
            _errorMessage.value = "Please fill all fields"
            return
        }

        _isLoading.value = true
        _errorMessage.value = null

        viewModelScope.launch {
            when (val result = customerRepository.login(emailValue, passwordValue)) {
                is Result.Success -> {
                    val customer = result.data

                    prefsManager.saveUserSession(
                            customer.id,
                            customer.name,
                            customer.email,
                            customer.phone,
                            customer.address
                    )
                    prefsManager.saveUserRole(SharedPreferencesManager.ROLE_CUSTOMER)

                    _loginSuccess.value = customer
                    _navigateToHome.value = true
                    _isLoading.value = false
                }
                is Result.Error -> {
                    _errorMessage.value = result.message
                    _isLoading.value = false
                }
                is Result.Loading -> {

                }
            }
        }
    }

    fun onNavigatedToHome() {
        _navigateToHome.value = false
    }
}

