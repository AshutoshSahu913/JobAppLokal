package com.example.jobapplokal.data_layer.repoImpl

import com.example.jobapplokal.data_layer.model.JobResponse
import com.example.jobapplokal.data_layer.network.ApiService
import com.example.jobapplokal.data_layer.room.JobDao
import com.example.jobapplokal.data_layer.room.JobResult
import com.example.jobapplokal.domain_layer.repo.Repo
import com.example.jobapplokal.utils.ResultState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class RepoImpl @Inject constructor(private val apiRef: ApiService, val jobDao: JobDao) : Repo {

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

    override suspend fun getBookmarkedJobs(): Flow<List<JobResult>>  {
        return jobDao.getBookmarkedJobs()
    }

    override suspend fun setBookmarkJob(job: JobResult) {
        jobDao.insertResult(job)
    }

    override suspend fun removeJobFromBookmarks(job: JobResult) {
        // Assuming there's a DAO function to remove a job using its ID
        jobDao.deleteJobById(job.id)
    }

    override suspend fun isJobBookmarked(jobId: String): Flow<Boolean> {
        // Assuming there's a DAO function to check if a job is bookmarked by its ID
        return jobDao.isJobBookmarked(jobId)
    }
}
