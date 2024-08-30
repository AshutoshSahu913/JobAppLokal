package com.example.jobapplokal.domain_layer.repo

import com.example.jobapplokal.data_layer.model.JobResponse
import com.example.jobapplokal.data_layer.room.JobResult
import com.example.jobapplokal.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface Repo {
    suspend fun getAllJobs(): Flow<ResultState<JobResponse>>

    suspend fun getBookmarkedJobs(): Flow<List<JobResult>>
    suspend fun setBookmarkJob(job: JobResult)

    suspend fun removeJobFromBookmarks(job: JobResult)
    suspend fun isJobBookmarked(jobId: String): Flow<Boolean>
}