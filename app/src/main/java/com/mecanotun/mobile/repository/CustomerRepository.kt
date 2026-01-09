package com.mecanotun.mobile.repository

import com.mecanotun.mobile.api.CustomerDto

import com.mecanotun.mobile.api.RepairSystemApi
import com.mecanotun.mobile.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class CustomerRepository(private val api: RepairSystemApi) {

    
    suspend fun login(email: String, password: String): Result<CustomerDto> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.loginCustomer(email, password)
                if (response.isSuccessful) {
                    val customer = response.body()
                    if (customer != null) {
                        Result.Success(customer)
                    } else {
                        Result.Error("Réponse vide du serveur")
                    }
                } else {
                    when (response.code()) {
                        401 -> Result.Error("Email ou mot de passe incorrect")
                        404 -> Result.Error("Compte introuvable")
                        else -> Result.Error("Erreur: ${response.code()} - ${response.message()}")
                    }
                }
            } catch (e: Exception) {
                Result.Error("Erreur réseau: ${e.message}", e)
            }
        }
    }

    
    suspend fun createCustomer(customerDto: CustomerDto): Result<CustomerDto> {
        return withContext(Dispatchers.IO) {
            try {
                val response =
                        api.createCustomer(
                                        name = customerDto.name,
                                        password = customerDto.password ?: "",
                                        phone = customerDto.phone,
                                        email = customerDto.email,
                                        address = customerDto.address
                                )
                if (response.isSuccessful) {
                    val customer = response.body()
                    if (customer != null) {
                        Result.Success(customer)
                    } else {
                        Result.Error("Réponse vide du serveur")
                    }
                } else {
                    when (response.code()) {
                        409 -> Result.Error("Un compte avec cet email existe déjà")
                        400 -> Result.Error("Données invalides")
                        else -> Result.Error("Erreur: ${response.code()} - ${response.message()}")
                    }
                }
            } catch (e: Exception) {
                Result.Error("Erreur réseau: ${e.message}", e)
            }
        }
    }

    
    suspend fun getCustomerById(customerId: Int): Result<CustomerDto> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getCustomerById(customerId)
                if (response.isSuccessful) {
                    val customer = response.body()
                    if (customer != null) {
                        Result.Success(customer)
                    } else {
                        Result.Error("Client introuvable")
                    }
                } else {
                    Result.Error("Erreur: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                Result.Error("Erreur réseau: ${e.message}", e)
            }
        }
    }

    
    suspend fun updateCustomer(
            oldEmail: String,
            customerDto: CustomerDto
    ): Result<CustomerDto> {
        return withContext(Dispatchers.IO) {
            try {
                val response =
                        api.updateCustomer(
                                        oldEmail = oldEmail,
                                        newName = customerDto.name,
                                        newPassword = customerDto.password ?: "",
                                        newPhone = customerDto.phone,
                                        newAddress = customerDto.address
                                )
                if (response.isSuccessful) {
                    val customer = response.body()
                    if (customer != null) {
                        Result.Success(customer)
                    } else {
                        Result.Error("Réponse vide du serveur")
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

