package com.mecanotun.mobile.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mecanotun.mobile.api.RetrofitClient
import com.mecanotun.mobile.api.TimeSlotDto
import com.mecanotun.mobile.repository.AppointmentRepository
import com.mecanotun.mobile.repository.MechanicRepository
import com.mecanotun.mobile.utils.Result
import com.mecanotun.mobile.utils.SharedPreferencesManager
import kotlinx.coroutines.launch

class TimeSlotSelectionViewModel(application: Application) : AndroidViewModel(application) {
    private val mechanicRepository = MechanicRepository(RetrofitClient.instance)
    private val appointmentRepository = AppointmentRepository(RetrofitClient.instance)
    private val prefsManager = SharedPreferencesManager(application)

    private val _timeSlots = MutableLiveData<List<TimeSlotDto>>()
    val timeSlots: LiveData<List<TimeSlotDto>> = _timeSlots

    private val _selectedSlot = MutableLiveData<TimeSlotDto?>()
    val selectedSlot: LiveData<TimeSlotDto?> = _selectedSlot

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    private val _successMessage = MutableLiveData<String?>()
    val successMessage: LiveData<String?> = _successMessage

    private val _bookingComplete = MutableLiveData<Boolean>(false)
    val bookingComplete: LiveData<Boolean> = _bookingComplete

    fun loadAvailableSlots(mechanicId: Int) {
        _isLoading.value = true
        _errorMessage.value = null

        viewModelScope.launch {
            try {

                when (val result = mechanicRepository.getMechanicById(mechanicId)) {
                    is Result.Success -> {
                        val mechanic = result.data
                        val allSlots = mechanic.timeSlots






                        
                        val availableSlots = allSlots?.filter { slot ->

                             slot.appointment.isNullOrEmpty()
                        } ?: emptyList()
                        
                        if (availableSlots.isEmpty()) {
                             val total = allSlots?.size ?: 0
                            _errorMessage.value = "No available slots for mechanic $mechanicId. Found total: $total slots."
                        }
                        _timeSlots.value = availableSlots
                    }
                    is Result.Error -> {
                        _errorMessage.value = result.message
                    }
                    is Result.Loading -> {}
                }
                _isLoading.value = false
            } catch (e: Exception) {
                _isLoading.value = false
                _errorMessage.value = "Error loading slots: ${e.message}"
            }
        }
    }

    fun selectSlot(slot: TimeSlotDto) {
        _selectedSlot.value = slot
    }

    fun confirmBooking(mechanicId: Int, vehicleId: String, serviceType: String, note: String) {
        val slot = _selectedSlot.value
        if (slot == null) return

        _isLoading.value = true
        _errorMessage.value = null
        val customerId = prefsManager.getUserId()

        val apiServiceType = if (serviceType.equals("OIL_CHANGE", ignoreCase = true)) "VIDANGE"
                             else serviceType.uppercase().replace("É", "E")

        viewModelScope.launch {
            try {

                val result = appointmentRepository.createAppointment(
                    customerId = customerId,
                    timeSlotId = slot.id.toString(), 
                    carId = vehicleId,
                    services = arrayOf(apiServiceType),
                    note = note
                )
                
                _isLoading.value = false
                
                when (result) {
                    is Result.Success -> {
                        _successMessage.value = "Appointment booked successfully!"
                        _bookingComplete.value = true
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
}

