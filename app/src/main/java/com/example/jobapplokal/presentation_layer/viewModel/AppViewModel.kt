package com.example.jobapplokal.presentation_layer.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobapplokal.data_layer.model.JobResponse
import com.example.jobapplokal.data_layer.room.JobResult
import com.example.jobapplokal.domain_layer.repo.Repo
import com.example.jobapplokal.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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


    fun setJobOffline(job: JobResult) {
        viewModelScope.launch {
            repo.setBookmarkJob(job = job)
        }
    }


    private val _jobsOffline = MutableStateFlow<List<JobResult>>(emptyList())
    val jobsOffline: StateFlow<List<JobResult>> = _jobsOffline
    fun getAllJobFromRoom() {
        viewModelScope.launch {
            repo.getBookmarkedJobs().collect { jobs ->
                _jobsOffline.value = jobs
            }
        }
    }

    fun removeJobFromBookmarks(job: JobResult) {
        viewModelScope.launch {
            repo.removeJobFromBookmarks(job)
        }
    }


    private val _bookmarkedJob = MutableStateFlow<Boolean>(false)
    val bookmarkedJob: StateFlow<Boolean> = _bookmarkedJob
    fun isJobBookmarked(jobId: String) {
        viewModelScope.launch {
            repo.isJobBookmarked(jobId).collect { isBookMarked ->
                _bookmarkedJob.value = isBookMarked
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