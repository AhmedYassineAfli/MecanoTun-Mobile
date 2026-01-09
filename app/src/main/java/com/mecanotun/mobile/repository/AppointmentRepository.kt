package com.mecanotun.mobile.repository

import com.mecanotun.mobile.api.AppointmentDto
import com.mecanotun.mobile.api.RepairSystemApi
import com.mecanotun.mobile.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class AppointmentRepository(private val api: RepairSystemApi) {

    
    suspend fun getAppointmentsByCustomerId(customerId: Int): Result<List<AppointmentDto>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getAppointmentsByCustomer(customerId)
                if (response.isSuccessful) {
                    val appointments = response.body() ?: emptyList()
                    Result.Success(appointments)
                } else {
                    Result.Error("Erreur: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                Result.Error("Erreur réseau: ${e.message}", e)
            }
        }
    }

    
    suspend fun createAppointment(
            customerId: Int,
            timeSlotId: String?,
            carId: String,
            services: Array<String>,
            note: String
    ): Result<AppointmentDto> {
        return withContext(Dispatchers.IO) {
            try {
                val response =
                        api.createAppointment(customerId, timeSlotId, carId, services, note)
                if (response.isSuccessful) {
                    val appointment = response.body()
                    if (appointment != null) {
                        Result.Success(appointment)
                    } else {
                        Result.Error("La réponse du serveur est vide")
                    }
                } else {
                    Result.Error("Erreur: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                Result.Error("Erreur réseau: ${e.message}", e)
            }
        }
    }

    
    suspend fun cancelAppointment(appointmentId: Int): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.deleteAppointment(appointmentId)
                if (response.isSuccessful) {
                    Result.Success(Unit)
                } else {
                    Result.Error("Erreur: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                Result.Error("Erreur réseau: ${e.message}", e)
            }
        }
    }
}

