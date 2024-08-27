package com.example.jobapplokal.presentation_layer.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jobapplokal.data_layer.model.JobResponse
import com.example.jobapplokal.data_layer.model.Location
import com.example.jobapplokal.presentation_layer.viewModel.AppViewModel
import com.example.jobapplokal.ui.theme.AppColor
import com.example.jobapplokal.utils.ResultState

@Composable
fun JobScreen(viewModel: AppViewModel, navController: NavHostController) {

    val jobState = viewModel.jobs.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    )
    {
        when (jobState) {
            is ResultState.Loading -> {
                LoadingScreen()
            }

            is ResultState.Success -> {
                JobList(jobState.data)
            }

            is ResultState.Error -> {
                ErrorScreen(message = (jobState as ResultState.Error).exception.localizedMessage!!.toString())
            }
        }
    }
}


@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            trackColor = Color.White,
            strokeWidth = 2.dp,
            color = AppColor
        )

    }
}


@Composable
fun JobList(jobs: JobResponse) {
    Text(text = jobs.results.size.toString(), color = Color.Black)
//    LazyColumn {
//        items(jobs.results) { job ->
//            JobCardItem(job.title, job.locations, job.salary_min, job.salary_max)
//        }
//    }
}


@Composable
fun ErrorScreen(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Error: $message")
    }
}

@Composable
fun JobCardItem(title: String, locations: List<Location>, salaryMin: Int, salaryMax: Int) {


}
