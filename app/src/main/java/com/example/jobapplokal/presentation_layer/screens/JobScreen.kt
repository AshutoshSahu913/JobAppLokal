package com.example.jobapplokal.presentation_layer.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jobapplokal.data_layer.model.Location
import com.example.jobapplokal.data_layer.model.Result
import com.example.jobapplokal.presentation_layer.viewModel.AppViewModel
import com.example.jobapplokal.ui.theme.AppColor
import com.example.jobapplokal.utils.ResultState

@Composable
fun JobScreen(viewModel: AppViewModel, navController: NavHostController) {

    LaunchedEffect(key1 = true) {
        viewModel.getAllJobs()
    }
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
                JobList(jobState.data.results)
            }

            is ResultState.Error -> {
                ErrorScreen(message = jobState.exception.localizedMessage!!.toString())
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
            strokeWidth = 3.dp,
            modifier = Modifier.size(50.dp),
            strokeCap = StrokeCap.Round,
            color = AppColor,
        )

    }
}


@Composable
fun JobList(jobs: List<Result>) {
    LazyColumn {
        items(jobs) { job ->
            JobCardItem(
                title = job.job_role,
                locations = job.locations,
                salaryMin = job.salary_min,
                salaryMax = job.salary_max,
                onClick = {

                }
            )

        }
    }
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
fun JobCardItem(
    title: String?,
    locations: List<Location>?,
    salaryMin: Int?,
    salaryMax: Int?,
    onClick: () -> Unit
) {

    val safeTitle = title ?: "Unknown Job Title"
    val safeLocations = locations ?: emptyList()
    val safeSalaryMin = salaryMin ?: 0
    val safeSalaryMax = salaryMax ?: 0

    // State to track whether the icon is bookmarked
    var isBookmarked by remember { mutableStateOf(false) }
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.Center) {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    color = AppColor,
                    text = safeTitle,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )

                Icon(
                    tint = if (isBookmarked) AppColor else Color.Black, // Change color based on state
                    imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder, // Change icon based on state
                    contentDescription = "",
                    modifier = Modifier
                        .alpha(if (isBookmarked) 1f else 0.3f)

                        .padding(5.dp)
                        .clip(CircleShape)
                        .clickable {
                            isBookmarked = !isBookmarked // Toggle the icon state on click
                        }
                        .background(
                            color = if (isBookmarked) Color.White else Color.White,
                            shape = CircleShape
                        )
                        .border(BorderStroke(1.dp, Color.LightGray), CircleShape)
                        .padding(10.dp)
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {

                Column {
                    safeLocations.forEachIndexed { index, location ->
                        Text(
                            text = "State: ${location.locale}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = FontFamily.Serif
                        )
                    }
                }
                Text(
                    modifier = Modifier,
                    text = "₹$safeSalaryMin - ₹$safeSalaryMax",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily.SansSerif
                )

            }

        }
    }
}
