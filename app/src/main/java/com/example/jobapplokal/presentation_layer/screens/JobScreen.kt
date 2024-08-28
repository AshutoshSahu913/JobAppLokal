package com.example.jobapplokal.presentation_layer.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jobapplokal.R
import com.example.jobapplokal.data_layer.model.PrimaryDetails
import com.example.jobapplokal.data_layer.model.Result
import com.example.jobapplokal.presentation_layer.navigation.NavigationRoutes
import com.example.jobapplokal.presentation_layer.viewModel.AppViewModel
import com.example.jobapplokal.ui.theme.AppColor
import com.example.jobapplokal.utils.ResultState

@Composable
fun JobScreen(viewModel: AppViewModel, navController: NavHostController) {

    LaunchedEffect(key1 = true) {
        viewModel.getAllJobs()
    }
    val jobState = viewModel.jobs.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    )
    {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Discover Your\nDream Jobs ",
                fontFamily = FontFamily.SansSerif,
                fontSize = 24.sp,
                lineHeight = TextUnit(30f, TextUnitType.Sp),
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                color = Color.Black,
                modifier = Modifier.padding(10.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.job1),
                contentDescription = "",
                modifier = Modifier
                    .size(50.dp)
                    .padding(5.dp)
            )
        }


        when (jobState) {
            is ResultState.Loading -> {
                LoadingScreen()
            }

            is ResultState.Success -> {
                JobList(jobState.data.results, navController)
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
            strokeWidth = 5.dp,
            modifier = Modifier.size(50.dp),
            strokeCap = StrokeCap.Round,
            color = AppColor,
        )

    }
}


@Composable
fun JobList(jobs: List<Result>, navController: NavHostController) {
    LazyColumn {
        items(jobs) { job ->
            JobCardItem(
                title = job.job_role,
                primaryDetails = job.primary_details,
                buttonText = job.button_text,
                customPhone = job.custom_link,
                onClick = {
                    navController.navigate(NavigationRoutes.JobDetailsScreen)
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
    primaryDetails: PrimaryDetails?,
    onClick: () -> Unit,
    customPhone: String?,
    buttonText: String?
) {

    val safeTitle = title ?: "Unknown Job Title"
    val jobLocation = primaryDetails?.Place ?: ""
    val jobSalary = primaryDetails?.Salary ?: ""
    val button_text = buttonText ?: ""
    val custom_link = customPhone ?: ""

    // State to track whether the icon is bookmarked
    var isBookmarked by remember { mutableStateOf(false) }
    Card(
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.Center) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    color = AppColor,
                    text = safeTitle,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier.padding(10.dp)
                )

                Text(
                    modifier = Modifier,
                    text = jobSalary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    fontFamily = FontFamily.Serif
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    tint = Color.Black, // Change color
                    imageVector = Icons.Default.LocationOn, // Change icon
                    contentDescription = "",
                    modifier = Modifier
                        .size(35.dp)
                        .padding(5.dp)
                        .clip(CircleShape)
                        .background(
                            color = Color.White,
                            shape = CircleShape
                        )
                        .border(BorderStroke(1.dp, Color.LightGray), CircleShape)
                        .padding(2.dp)
                )
                Text(
                    text = jobLocation,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    fontFamily = FontFamily.Serif, fontStyle = FontStyle.Normal
                )

            }


            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
            ) {
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = button_text,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    fontFamily = FontFamily.Serif, fontStyle = FontStyle.Normal
                )
                Text(
                    text = custom_link.removePrefix("tel:"),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    fontFamily = FontFamily.Serif, fontStyle = FontStyle.Normal
                )

            }

            HorizontalDivider(modifier = Modifier.padding(10.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {

                Button(
                    modifier = Modifier
                        .weight(0.8f)
                        .padding(horizontal = 10.dp, vertical = 10.dp),
                    onClick = { onClick.invoke()},
                    colors = ButtonDefaults.buttonColors(AppColor),
                    shape = RoundedCornerShape(10.dp)

                ) {
                    Text(
                        modifier = Modifier,
                        text = "Apply",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily.Serif
                    )
                }

                Icon(
                    tint = if (isBookmarked) AppColor else Color.Black, // Change color
                    imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder, // Change icon
                    contentDescription = "",
                    modifier = Modifier
                        .alpha(if (isBookmarked) 1f else 0.3f)
                        .padding(5.dp)
                        .clip(CircleShape)
                        .clickable {
                            isBookmarked = !isBookmarked
                        }
                        .background(
                            color = if (isBookmarked) Color.White else Color.White,
                            shape = CircleShape
                        )
                        .border(BorderStroke(1.dp, Color.LightGray), CircleShape)
                        .padding(10.dp)
                )
            }
            }
        }

}

