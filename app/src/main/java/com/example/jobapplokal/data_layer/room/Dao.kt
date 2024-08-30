package com.example.jobapplokal.data_layer.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.jobapplokal.data_layer.model.Result
import kotlinx.coroutines.flow.Flow

@Dao
interface JobDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResult(result: JobResult)

    @Query("SELECT * FROM job_details")
     fun getBookmarkedJobs(): Flow<List<JobResult>>

    @Query("DELETE FROM job_details WHERE id = :jobId")
    suspend fun deleteJobById(jobId: Int)

    @Query("SELECT COUNT(*) > 0 FROM job_details WHERE id = :jobId")
    fun isJobBookmarked(jobId: String): Flow<Boolean>
}