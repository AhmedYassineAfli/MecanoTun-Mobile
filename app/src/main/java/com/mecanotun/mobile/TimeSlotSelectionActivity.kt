package com.mecanotun.mobile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mecanotun.mobile.api.TimeSlotDto
import com.mecanotun.mobile.databinding.ActivityTimeSlotSelectionBinding
import com.mecanotun.mobile.utils.Constants
import com.mecanotun.mobile.viewmodel.TimeSlotSelectionViewModel

class TimeSlotSelectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTimeSlotSelectionBinding
    private lateinit var viewModel: TimeSlotSelectionViewModel
    private var mechanicId: Int = 0
    private var vehicleId: String = ""
    private var serviceType: String = ""
    private var note: String = ""
    private val adapter = TimeSlotAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_time_slot_selection)
        viewModel = ViewModelProvider(this)[TimeSlotSelectionViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setupRecyclerView()
        getIntentData()
        observeViewModel()
        
        if (mechanicId != 0) {
            viewModel.loadAvailableSlots(mechanicId)
        }

        binding.btnConfirmSelection.setOnClickListener {
            viewModel.confirmBooking(mechanicId, vehicleId, serviceType, note)
        }
    }

    private fun getIntentData() {



        mechanicId = intent.getIntExtra("MECHANIC_ID", 0)
        vehicleId = intent.getStringExtra("VEHICLE_ID") ?: ""
        serviceType = intent.getStringExtra("SERVICE_TYPE") ?: ""
        note = intent.getStringExtra("NOTE") ?: ""
    }

    private fun setupRecyclerView() {
        binding.rvTimeSlots.layoutManager = LinearLayoutManager(this)
        binding.rvTimeSlots.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.timeSlots.observe(this) { slots ->
            adapter.submitList(slots)
            if (slots.isEmpty() && viewModel.isLoading.value == false) {

            }
        }

        viewModel.bookingComplete.observe(this) { complete ->
            if (complete) {
                Toast.makeText(this, "Appointment Booked Successfully!", Toast.LENGTH_LONG).show()
                finish()
            }
        }
        
        viewModel.errorMessage.observe(this) { msg ->
            if (msg != null) {
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            }
        }
    }

    inner class TimeSlotAdapter : RecyclerView.Adapter<TimeSlotAdapter.ViewHolder>() {
        private var items: List<TimeSlotDto> = emptyList()
        private var selectedPosition = -1

        fun submitList(newItems: List<TimeSlotDto>) {
            items = newItems
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_time_slot, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(items[position], position)
        }

        override fun getItemCount() = items.size

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val tvStartDay: TextView = itemView.findViewById(R.id.tv_start_day)
            private val tvTimeRange: TextView = itemView.findViewById(R.id.tv_time_range)
            private val ivSelected: ImageView = itemView.findViewById(R.id.iv_selected_check)

            fun bind(slot: TimeSlotDto, position: Int) {

                tvStartDay.text = slot.startDay


                val startTime = slot.startTimeFormat ?: ""
                val endTime = slot.endTimeFormat ?: ""

                val cleanStart = if(startTime.length > 5) startTime.substring(0, 5) else startTime
                val cleanEnd = if(endTime.length > 5) endTime.substring(0, 5) else endTime
                
                tvTimeRange.text = "$cleanStart - $cleanEnd"

                val isSelected = position == selectedPosition
                ivSelected.visibility = if (isSelected) View.VISIBLE else View.GONE
                itemView.isSelected = isSelected
                
                itemView.setOnClickListener {
                    val previousSelected = selectedPosition
                    selectedPosition = adapterPosition
                    notifyItemChanged(previousSelected)
                    notifyItemChanged(selectedPosition)
                    
                    viewModel.selectSlot(slot)
                }
            }
        }
    }
}

