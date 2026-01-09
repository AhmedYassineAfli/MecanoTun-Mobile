package com.mecanotun.mobile.api

data class TimeSlotDto(
    val id: Int,
    val startDay: String?,
    val startTimeFormat: String?,
    val endTimeFormat: String?,
    val mechanic: List<MechanicDto>?,
    val appointment: List<AppointmentDto>?
)

