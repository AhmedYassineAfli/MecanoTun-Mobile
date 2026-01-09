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

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val prefsManager = SharedPreferencesManager(application)
    private val customerRepository = CustomerRepository(RetrofitClient.instance)

    val name = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val phone = MutableLiveData<String>()
    val address = MutableLiveData<String>()
    val password = MutableLiveData<String>("")

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    private val _successMessage = MutableLiveData<String?>()
    val successMessage: LiveData<String?> = _successMessage

    init {
        loadUserData()
    }

    private fun loadUserData() {
        name.value = prefsManager.getUserName() ?: ""
        email.value = prefsManager.getUserEmail() ?: ""
        phone.value = prefsManager.getUserPhone() ?: ""
        address.value = prefsManager.getUserAddress() ?: ""
    }

    fun updateProfile() {
        val userEmail = prefsManager.getUserEmail() ?: return 
        val newName = name.value?.trim() ?: ""
        val newEmail = email.value?.trim() ?: ""
        val newPhone = phone.value?.trim() ?: ""
        val newAddress = address.value?.trim() ?: ""
        val newPassword = password.value?.trim() ?: ""

        if (newName.isEmpty() || newPhone.isEmpty() || newAddress.isEmpty() || newPassword.isEmpty()
        ) {
            _errorMessage.value = "Please fill all fields"
            return
        }

        _isLoading.value = true
        _errorMessage.value = null

        viewModelScope.launch {
            val customerDto =
                    CustomerDto(
                            id = prefsManager.getUserId(),
                            name = newName,
                            email = newEmail,
                            password = newPassword,
                            phone = newPhone,
                            address = newAddress
                    )

            when (val result = customerRepository.updateCustomer(userEmail, customerDto)) {
                is Result.Success -> {
                    val customer = result.data

                    prefsManager.saveUserSession(
                            customer.id,
                            customer.name,
                            customer.email,
                            customer.phone,
                            customer.address
                    )

                    _successMessage.value = "Profile updated successfully"
                    password.value = ""
                    loadUserData()
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
}

