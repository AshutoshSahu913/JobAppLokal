package com.example.jobapplokal.domain_layer.repo

import com.example.jobapplokal.data_layer.model.JobResponse
import com.example.jobapplokal.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface Repo {
    suspend fun getAllJobs(): Flow<ResultState<JobResponse>>
}