package com.mecanotun.mobile.repository

import com.mecanotun.mobile.api.RepairSystemApi
import com.mecanotun.mobile.api.ServiceDto
import com.mecanotun.mobile.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class ServiceRepository(private val api: RepairSystemApi) {

    
    suspend fun getAllServices(): Result<List<ServiceDto>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getAllServices()
                if (response.isSuccessful) {
                    val services = response.body() ?: emptyList()
                    Result.Success(services)
                } else {
                    Result.Error("Erreur: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                Result.Error("Erreur réseau: ${e.message}", e)
            }
        }
    }

    
    suspend fun getServiceById(serviceType: String): Result<ServiceDto> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getServiceById(serviceType)
                if (response.isSuccessful) {
                    val service = response.body()
                    if (service != null) {
                        Result.Success(service)
                    } else {
                        Result.Error("Service introuvable")
                    }
                } else {
                    Result.Error("Erreur: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                Result.Error("Erreur réseau: ${e.message}", e)
            }
        }
    }
}

