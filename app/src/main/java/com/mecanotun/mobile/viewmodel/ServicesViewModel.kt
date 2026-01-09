package com.mecanotun.mobile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mecanotun.mobile.api.RetrofitClient
import com.mecanotun.mobile.api.ServiceDto
import com.mecanotun.mobile.repository.ServiceRepository
import com.mecanotun.mobile.utils.Result
import kotlinx.coroutines.launch

class ServicesViewModel : ViewModel() {
    private val serviceRepository = ServiceRepository(RetrofitClient.instance)

    private val _services = MutableLiveData<List<ServiceDto>>()
    val services: LiveData<List<ServiceDto>> = _services

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    private val _isEmpty = MutableLiveData<Boolean>(false)
    val isEmpty: LiveData<Boolean> = _isEmpty

    private val _navigateToBooking = MutableLiveData<ServiceDto?>()
    val navigateToBooking: LiveData<ServiceDto?> = _navigateToBooking

    init {
        loadServices()
    }

    fun loadServices() {
        _isLoading.value = true
        _errorMessage.value = null

        viewModelScope.launch {
            when (val result = serviceRepository.getAllServices()) {
                is Result.Success -> {
                    _services.value = result.data
                    _isEmpty.value = result.data.isEmpty()
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

    fun onServiceClicked(service: ServiceDto) {
        _navigateToBooking.value = service
    }

    fun onNavigatedToBooking() {
        _navigateToBooking.value = null
    }
}

