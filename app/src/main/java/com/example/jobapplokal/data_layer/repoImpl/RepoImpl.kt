package com.example.jobapplokal.data_layer.repoImpl

import com.example.jobapplokal.data_layer.model.JobResponse
import com.example.jobapplokal.data_layer.network.ApiService
import com.example.jobapplokal.domain_layer.repo.Repo
import com.example.jobapplokal.utils.ResultState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class RepoImpl @Inject constructor(private val apiRef: ApiService) : Repo {

    override suspend fun getAllJobs(): Flow<ResultState<JobResponse>> = callbackFlow {
        try {
            trySend(ResultState.Loading)

            // Fetch data from API
            val response = apiRef.getAllJobs()
            // Emit success state with data
            trySend(ResultState.Success(response))
        } catch (e: Exception) {
            // Emit error state
            trySend(ResultState.Error(e))
        }

        // Close the flow
        awaitClose { close() }
    }

}
