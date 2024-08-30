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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jobapplokal.data_layer.model.PrimaryDetails
import com.example.jobapplokal.data_layer.model.Result
import com.example.jobapplokal.presentation_layer.navigation.NavigationRoutes
import com.example.jobapplokal.presentation_layer.viewModel.AppViewModel
import com.example.jobapplokal.presentation_layer.viewModel.SharedViewModel
import com.example.jobapplokal.ui.theme.AppColor

@Composable
fun BookmarksScreen(
    viewModel: AppViewModel,
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {

    LaunchedEffect(key1 = true) {
        viewModel.getAllJobFromRoom()
    }
    val offlineJobs = viewModel.jobsOffline.collectAsState().value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    )
    {
    if(offlineJobs.isEmpty()){
        Text(
            textAlign = TextAlign.Center,
            text = "Offline save Job ",
            modifier = Modifier
                .align(Alignment.Center),
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            color = Color.Black, fontSize = 15.sp
        )

    }else{

        LazyColumn {
            items(offlineJobs) { offlineJob ->

                LaunchedEffect(key1 = offlineJob.id) {
                    viewModel.isJobBookmarked(offlineJob.id.toString())
                }
                // Observe if the job is already bookmarked
                val isBookmarkedState = viewModel.bookmarkedJob.collectAsState().value
                val isBookmarked = remember { mutableStateOf(isBookmarkedState) }
                val result = Result(
                    advertiser = offlineJob.advertiser,
                    amount = offlineJob.amount,
                    button_text = offlineJob.button_text,
                    city_location = offlineJob.city_location,
                    company_name = offlineJob.company_name,
                    contact_preference = offlineJob.contact_preference,
                    content = offlineJob.content,
                    contentV3 = offlineJob.contentV3,
                    created_on = offlineJob.created_on,
                    creatives = offlineJob.creatives,
                    custom_link = offlineJob.custom_link,
                    enable_lead_collection = offlineJob.enable_lead_collection,
                    experience = offlineJob.experience,
                    expire_on = offlineJob.expire_on,
                    fb_shares = offlineJob.fb_shares,
                    fee_details = offlineJob.fee_details,
                    fees_charged = offlineJob.fees_charged,
                    fees_text = offlineJob.fees_text,
                    id = offlineJob.id,
                    is_applied = offlineJob.is_applied,
                    is_bookmarked = offlineJob.is_bookmarked,
                    is_job_seeker_profile_mandatory = offlineJob.is_job_seeker_profile_mandatory,
                    is_owner = offlineJob.is_owner,
                    is_premium = offlineJob.is_premium,
                    job_category = offlineJob.job_category,
                    job_category_id = offlineJob.job_category_id,
                    job_hours = offlineJob.job_hours,
                    job_location_slug = offlineJob.job_location_slug,
                    job_role = offlineJob.job_role,
                    job_role_id = offlineJob.job_role_id,
                    job_tags = offlineJob.job_tags,
                    job_type = offlineJob.job_type,
                    locality = offlineJob.locality,
                    locations = offlineJob.locations,
                    num_applications = offlineJob.num_applications,
                    openings_count = offlineJob.openings_count,
                    other_details = offlineJob.other_details,
                    premium_till = offlineJob.premium_till,
                    primary_details = offlineJob.primary_details,
                    qualification = offlineJob.qualification,
                    salary_max = offlineJob.salary_max,
                    salary_min = offlineJob.salary_min,
                    shares = offlineJob.shares,
                    shift_timing = offlineJob.shift_timing,
                    should_show_last_contacted = offlineJob.should_show_last_contacted,
                    status = offlineJob.status,
                    tags = offlineJob.tags,
                    title = offlineJob.title,
                    translated_content = offlineJob.translated_content,
                    type = offlineJob.type,
                    updated_on = offlineJob.updated_on,
                    videos = offlineJob.videos,
                    views = offlineJob.views,
                    whatsapp_no = offlineJob.whatsapp_no,
                    screening_retry = null,
                    question_bank_id = null
                )

                OfflineJobCardItem(
                    title = offlineJob.job_role,
                    primaryDetails = offlineJob.primary_details,
                    buttonText = offlineJob.button_text,
                    customPhone = offlineJob.custom_link,
                    isBookmarked = isBookmarked,
                    viewModel = viewModel,
                    onClick = {
                        //save list of jobDetails in viewModel
                        sharedViewModel.setDetails(job = result)
                        navController.navigate(NavigationRoutes.JobDetailsScreen)
                    }
                )

            }
        }
    }
    }

}


@Composable
fun OfflineJobCardItem(
    title: String?,
    primaryDetails: PrimaryDetails?,
    onClick: () -> Unit,
    customPhone: String?,
    buttonText: String?,
    viewModel: AppViewModel,
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
                    onClick = { onClick.invoke() },
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
//                            if (isBookmarked.value) {
//                                viewModel.removeJobFromBookmarks(jobResult)
//
//                            } else {
//                                viewModel.setJobOffline(jobResult)
//                            }
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