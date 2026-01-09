package com.mecanotun.mobile.repository

import com.mecanotun.mobile.api.MechanicDto
import com.mecanotun.mobile.api.RepairSystemApi
import com.mecanotun.mobile.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class MechanicRepository(private val api: RepairSystemApi) {

    
    suspend fun getAllMechanics(): Result<List<MechanicDto>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getAllMechanics()
                if (response.isSuccessful) {
                    val mechanics = response.body() ?: emptyList()
                    Result.Success(mechanics)
                } else {
                    Result.Error("Erreur: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                Result.Error("Erreur réseau: ${e.message}", e)
            }
        }
    }

    
    suspend fun getMechanicById(mechanicId: Int): Result<MechanicDto> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getMechanicById(mechanicId)
                if (response.isSuccessful) {
                    val mechanic = response.body()
                    if (mechanic != null) {
                        Result.Success(mechanic)
                    } else {
                        Result.Error("Mécanicien introuvable")
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

