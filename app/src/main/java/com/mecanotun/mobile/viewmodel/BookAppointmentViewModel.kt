package com.mecanotun.mobile.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mecanotun.mobile.api.CarDto
import com.mecanotun.mobile.api.MechanicDto
import com.mecanotun.mobile.api.RetrofitClient
import com.mecanotun.mobile.repository.AppointmentRepository
import com.mecanotun.mobile.repository.MechanicRepository
import com.mecanotun.mobile.utils.Result
import com.mecanotun.mobile.utils.SharedPreferencesManager
import kotlinx.coroutines.launch

class BookAppointmentViewModel(application: Application) : AndroidViewModel(application) {
    private val prefsManager = SharedPreferencesManager(application)
    private val mechanicRepository = MechanicRepository(RetrofitClient.instance)
    private val appointmentRepository = AppointmentRepository(RetrofitClient.instance)

    private val _mechanics = MutableLiveData<List<MechanicDto>>()
    val mechanics: LiveData<List<MechanicDto>> = _mechanics

    private val _vehicles = MutableLiveData<List<CarDto>>()
    val vehicles: LiveData<List<CarDto>> = _vehicles

    private val _selectedMechanicPosition = MutableLiveData<Int>(0)
    val selectedMechanicPosition: LiveData<Int> = _selectedMechanicPosition

    private val _selectedVehiclePosition = MutableLiveData<Int>(0)
    val selectedVehiclePosition: LiveData<Int> = _selectedVehiclePosition

    val note = MutableLiveData<String>("")

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    private val _successMessage = MutableLiveData<String?>()
    val successMessage: LiveData<String?> = _successMessage

    private val _finishActivity = MutableLiveData<Boolean>(false)
    val finishActivity: LiveData<Boolean> = _finishActivity

    var serviceType: String = ""

    fun loadData() {
        _isLoading.value = true
        val customerId = prefsManager.getUserId()

        viewModelScope.launch {
            try {

                when (val mechanicsResult = mechanicRepository.getAllMechanics()) {
                    is Result.Success -> {
                        _mechanics.value = mechanicsResult.data
                    }
                    is Result.Error -> {
                        _errorMessage.value = mechanicsResult.message
                    }
                    is Result.Loading -> {}
                }

                val vehiclesResponse = RetrofitClient.instance.getCarsByCustomer(customerId)
                if (vehiclesResponse.isSuccessful && vehiclesResponse.body() != null) {
                    _vehicles.value = vehiclesResponse.body()!!
                }

                _isLoading.value = false
            } catch (e: Exception) {
                _isLoading.value = false
                _errorMessage.value = "Error loading data: ${e.message}"
            }
        }
    }

    fun bookAppointment() {
        val mechanicsList = _mechanics.value
        val vehiclesList = _vehicles.value

        if (mechanicsList.isNullOrEmpty()) {
            _errorMessage.value = "No mechanics available"
            return
        }

        if (vehiclesList.isNullOrEmpty()) {
            _errorMessage.value = "Please add a vehicle first"
            return
        }

        val selectedMechanic = mechanicsList[_selectedMechanicPosition.value ?: 0]
        val selectedVehicle = vehiclesList[_selectedVehiclePosition.value ?: 0]
        val noteValue = note.value?.trim() ?: ""
        val customerId = prefsManager.getUserId()

        val apiServiceType =
                if (serviceType.equals("OIL_CHANGE", ignoreCase = true)) {
                    "VIDANGE" 
                } else {
                    serviceType.uppercase()
                }

        _isLoading.value = true
        _errorMessage.value = null

        viewModelScope.launch {
            try {
                val result =
                        appointmentRepository.createAppointment(
                                customerId = customerId,
                                timeSlotId = "auto",
                                carId = selectedVehicle.id.toString(),
                                services = arrayOf(apiServiceType),
                                note = noteValue
                        )

                _isLoading.value = false

                when (result) {
                    is Result.Success -> {
                        _successMessage.value =
                                "Appointment booked successfully with ${selectedMechanic.name}!"
                        _finishActivity.value = true
                    }
                    is Result.Error -> {
                        _errorMessage.value = "Booking failed: ${result.message}"
                    }
                    is Result.Loading -> {}
                }
            } catch (e: Exception) {
                _isLoading.value = false
                _errorMessage.value = "Error: ${e.message}"
            }
        }
    }

    fun setSelectedMechanicPosition(position: Int) {
        _selectedMechanicPosition.value = position
    }

    fun setSelectedVehiclePosition(position: Int) {
        _selectedVehiclePosition.value = position
    }
}

