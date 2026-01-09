package com.mecanotun.mobile.api

data class CustomerDto(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    val address: String,
    val password: String? = null
)

