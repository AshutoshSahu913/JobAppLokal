package com.example.jobapplokal.presentation_layer.viewModel

import androidx.lifecycle.ViewModel
import com.example.jobapplokal.data_layer.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
) : ViewModel() {

    private val _jobsDetails = MutableStateFlow<Result?>(null)  // If Result is nullable
    val jobsDetails: StateFlow<Result?> = _jobsDetails

    fun setDetails(job: Result) {
        _jobsDetails.value = job
    }

}