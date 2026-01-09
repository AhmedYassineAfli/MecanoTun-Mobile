package com.mecanotun.mobile.repository

import com.mecanotun.mobile.api.RepairSystemApi
import com.mecanotun.mobile.api.TimeSlotDto
import com.mecanotun.mobile.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TimeSlotRepository(private val api: RepairSystemApi) {

    suspend fun getAvailableTimeSlots(): Result<List<TimeSlotDto>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getAvailableTimeSlots() 

                if (response.isSuccessful) {
                    val slots = response.body() ?: emptyList()
                    Result.Success(slots)
                } else {
                    Result.Error("Erreur: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                Result.Error("Erreur réseau: ${e.message}", e)
            }
        }
    }
}

