package com.example.jobapplokal.presentation_layer.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobapplokal.data_layer.model.JobResponse
import com.example.jobapplokal.domain_layer.repo.Repo
import com.example.jobapplokal.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val repo: Repo
) : ViewModel() {

    init {
        getAllJobs()
    }

    private var _jobs: MutableState<ResultState<JobResponse>> = mutableStateOf(ResultState.Loading)
    val jobs: MutableState<ResultState<JobResponse>> = _jobs

    fun getAllJobs() {
        viewModelScope.launch {
            try {
                repo.getAllJobs().collect { jobList ->
                    when (jobList) {
                        is ResultState.Success -> {
                            Log.d("ViewModel", "ViewModel ----getAllJobs:-------${jobList.data.results.size}")
                            Log.d("ViewModel", "ViewModel ----getAllJobs:-------${jobList.data.results[2].title}")
                            _jobs.value = jobList

                        }
                        is ResultState.Error -> {
                            Log.e("ViewModel", "Error fetching jobs: ${jobList.exception.message}")
                        }
                        else -> {
                            Log.d("ViewModel", "Loading or some other state")
                        }
                    }

                }
            } catch (e: Exception) {
                _jobs.value = ResultState.Error(e)
                Log.e("ViewModel", "Exception fetching jobs: ${e.message}")
            }
        }
    }
}


sealed class JobsUiState {
    data object Idle : JobsUiState()
    data object Loading : JobsUiState()
    data object Success : JobsUiState()
    data object Error : JobsUiState()
}