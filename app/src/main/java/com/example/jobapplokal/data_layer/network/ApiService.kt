package com.example.jobapplokal.data_layer.network

import com.example.jobapplokal.data_layer.model.JobResponse
import retrofit2.http.GET

interface ApiService {

    @GET("/common/jobs")
    suspend fun getAllJobs(
    ): JobResponse

}