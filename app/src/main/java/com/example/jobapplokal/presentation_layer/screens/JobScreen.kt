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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.jobapplokal.data_layer.room.JobResult
import com.example.jobapplokal.presentation_layer.navigation.NavigationRoutes
import com.example.jobapplokal.presentation_layer.viewModel.AppViewModel
import com.example.jobapplokal.presentation_layer.viewModel.SharedViewModel
import com.example.jobapplokal.ui.theme.AppColor
import com.example.jobapplokal.utils.ResultState

@Composable
fun JobScreen(
    viewModel: AppViewModel,
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {

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
                JobList(jobState.data.results, navController, viewModel,sharedViewModel)
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
fun JobList(
    jobs: List<Result>,
    navController: NavHostController,
    viewModel: AppViewModel,
    sharedViewModel: SharedViewModel
) {
    LazyColumn {
        items(jobs) { job ->
            val jobResult = JobResult(
                advertiser = job.advertiser ?: 0,
                amount = job.amount ?: "",
                button_text = job.button_text ?: "",
                city_location = job.city_location ?: 0,
                company_name = job.company_name ?: "",
                contact_preference = job.contact_preference,
                content = job.content ?: "",
                contentV3 = job.contentV3,
                created_on = job.created_on ?: "",
                creatives = job.creatives ?: listOf(),
                custom_link = job.custom_link ?: "",
                enable_lead_collection = job.enable_lead_collection ?: false,
                experience = job.experience ?: 0,
                expire_on = job.expire_on ?: "",
                fb_shares = job.fb_shares ?: 0,
                fee_details = job.fee_details,
                fees_charged = job.fees_charged ?: 0,
                fees_text = job.fees_text ?: "",
                id = job.id ?: 0,
                is_applied = job.is_applied ?: false,
                is_bookmarked = job.is_bookmarked ?: false,
                is_job_seeker_profile_mandatory = job.is_job_seeker_profile_mandatory ?: false,
                is_owner = job.is_owner ?: false,
                is_premium = job.is_premium ?: false,
                job_category = job.job_category ?: "",
                job_category_id = job.job_category_id ?: 0,
                job_hours = job.job_hours ?: "",
                job_location_slug = job.job_location_slug ?: "",
                job_role = job.job_role ?: "",
                job_role_id = job.job_role_id ?: 0,
                job_tags = job.job_tags ?: listOf(),
                job_type = job.job_type ?: 0,
                locality = job.locality ?: 0,
                locations = job.locations ?: listOf(),
                num_applications = job.num_applications ?: 0,
                openings_count = job.openings_count ?: 0,
                other_details = job.other_details ?: "",
                premium_till = job.premium_till ?: "",
                primary_details = job.primary_details,
                qualification = job.qualification ?: 0,
                salary_max = job.salary_max ?: 0,
                salary_min = job.salary_min ?: 0,
                shares = job.shares ?: 0,
                shift_timing = job.shift_timing ?: 0,
                should_show_last_contacted = job.should_show_last_contacted ?: false,
                status = job.status ?: 0,
                tags = job.tags ?: listOf(),
                title = job.title ?: "",
                translated_content = job.translated_content,
                type = job.type ?: 0,
                updated_on = job.updated_on ?: "",
                videos = job.videos ?: listOf(),
                views = job.views ?: 0,
                whatsapp_no = job.whatsapp_no ?: ""
            )


            LaunchedEffect(key1 = job.id) {
                viewModel.isJobBookmarked(job.id.toString())
            }
            // Observe if the job is already bookmarked
            val isBookmarkedState = viewModel.bookmarkedJob.collectAsState().value
            val isBookmarked = remember { mutableStateOf(isBookmarkedState) }

            JobCardItem(
                title = job.job_role,
                primaryDetails = job.primary_details,
                buttonText = job.button_text,
                customPhone = job.custom_link,
                isBookmarked =isBookmarked,
                jobResult = jobResult,
                viewModel = viewModel,
                onClick = {

                    //save list of jobDetails in viewModel
                    sharedViewModel.setDetails(job)
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
    buttonText: String?,
    viewModel: AppViewModel,  // Add ViewModel here
    jobResult: JobResult,
    isBookmarked: MutableState<Boolean>
) {

    val safeTitle = title ?: "Unknown Job Title"
    val jobLocation = primaryDetails?.Place ?: ""
    val jobSalary = primaryDetails?.Salary ?: ""
    val button_text = buttonText ?: ""
    val custom_link = customPhone ?: ""

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
                    tint = if (isBookmarked.value) AppColor else Color.Black, // Change color
                    imageVector = if (isBookmarked.value) Icons.Default.Bookmark else Icons.Default.BookmarkBorder, // Change icon
                    contentDescription = "",
                    modifier = Modifier
                        .alpha(if (isBookmarked.value) 1f else 0.3f)
                        .padding(5.dp)
                        .clip(CircleShape)
                        .clickable {
                            if (isBookmarked.value) {
                                viewModel.removeJobFromBookmarks(jobResult)

                            } else {
                                viewModel.setJobOffline(jobResult)
                            }
                            isBookmarked.value = !isBookmarked.value
                        }
                        .background(
                            color = if (isBookmarked.value) Color.White else Color.White,
                            shape = CircleShape
                        )
                        .border(BorderStroke(1.dp, Color.LightGray), CircleShape)
                        .padding(10.dp)
                )
            }
        }
    }
}