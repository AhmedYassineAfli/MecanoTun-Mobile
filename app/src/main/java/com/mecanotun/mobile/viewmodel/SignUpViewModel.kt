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
import kotlinx.coroutines.launch

class SignUpViewModel(application: Application) : AndroidViewModel(application) {
    private val customerRepository = CustomerRepository(RetrofitClient.instance)

    val name = MutableLiveData<String>("")
    val email = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")
    val phone = MutableLiveData<String>("")
    val address = MutableLiveData<String>("")

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    private val _successMessage = MutableLiveData<String?>()
    val successMessage: LiveData<String?> = _successMessage

    private val _navigateToLogin = MutableLiveData<Boolean>(false)
    val navigateToLogin: LiveData<Boolean> = _navigateToLogin

    fun signUp() {
        val nameValue = name.value?.trim() ?: ""
        val emailValue = email.value?.trim() ?: ""
        val passwordValue = password.value?.trim() ?: ""
        val phoneValue = phone.value?.trim() ?: ""
        val addressValue = address.value?.trim() ?: ""

        if (nameValue.isEmpty() ||
                        emailValue.isEmpty() ||
                        passwordValue.isEmpty() ||
                        phoneValue.isEmpty() ||
                        addressValue.isEmpty()
        ) {
            _errorMessage.value = "Please fill all fields"
            return
        }

        if (passwordValue.length < 6) {
            _errorMessage.value = "Password must be at least 6 characters"
            return
        }

        _isLoading.value = true
        _errorMessage.value = null

        viewModelScope.launch {
            val customerDto =
                    CustomerDto(
                            id = 0, 
                            name = nameValue,
                            email = emailValue,
                            password = passwordValue,
                            phone = phoneValue,
                            address = addressValue
                    )

            when (val result = customerRepository.createCustomer(customerDto)) {
                is Result.Success -> {
                    _successMessage.value = "Account created successfully!"
                    _navigateToLogin.value = true
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

    fun onNavigatedToLogin() {
        _navigateToLogin.value = false
    }
}

