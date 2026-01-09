package com.mecanotun.mobile

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mecanotun.mobile.databinding.ActivityBookAppointmentBinding
import com.mecanotun.mobile.utils.Constants
import com.mecanotun.mobile.viewmodel.BookAppointmentViewModel

class BookAppointmentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookAppointmentBinding
    private lateinit var viewModel: BookAppointmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Book Appointment"

        binding = ActivityBookAppointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[BookAppointmentViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.serviceType = intent.getStringExtra(Constants.EXTRA_SERVICE_NAME) ?: "OIL_CHANGE"

        viewModel.loadData()

        viewModel.mechanics.observe(this) { mechanics ->
            val mechanicNames = mechanics.map { it.name }
            binding.spinnerMechanic.adapter =
                    ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, mechanicNames)
        }

        viewModel.vehicles.observe(this) { vehicles ->
            val vehicleNames = vehicles.map { "${it.brand} ${it.model} (${it.year ?: "N/A"})" }
            binding.spinnerVehicle.adapter =
                    ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, vehicleNames)
        }

        binding.spinnerMechanic.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                    ) {
                        viewModel.setSelectedMechanicPosition(position)
                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                }

        binding.spinnerVehicle.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                    ) {
                        viewModel.setSelectedVehiclePosition(position)
                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                }

        viewModel.errorMessage.observe(this) { error ->
            error?.let { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }
        }

        viewModel.successMessage.observe(this) { success ->
            success?.let { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }
        }

        viewModel.finishActivity.observe(this) { shouldFinish ->
            if (shouldFinish) {
                finish()
            }
        }


        binding.btnSubmitBooking.text = "Select Date & Time"
        binding.btnSubmitBooking.setOnClickListener {

            val mechanicsList = viewModel.mechanics.value
            val selectedMechanicPosition = viewModel.selectedMechanicPosition.value ?: 0
            if (mechanicsList.isNullOrEmpty()) {
                Toast.makeText(this, "No mechanics available", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val selectedMechanic = mechanicsList[selectedMechanicPosition]

             val vehiclesList = viewModel.vehicles.value
            val selectedVehiclePosition = viewModel.selectedVehiclePosition.value ?: 0
            if (vehiclesList.isNullOrEmpty()) {
                Toast.makeText(this, "Please add a vehicle first", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val selectedVehicle = vehiclesList[selectedVehiclePosition]
            
            val noteValue = viewModel.note.value?.trim() ?: ""

            val intent = android.content.Intent(this, TimeSlotSelectionActivity::class.java).apply {
                putExtra("MECHANIC_ID", selectedMechanic.id)
                putExtra("VEHICLE_ID", selectedVehicle.id.toString())
                putExtra("SERVICE_TYPE", viewModel.serviceType)
                putExtra("NOTE", noteValue)
            }
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}

