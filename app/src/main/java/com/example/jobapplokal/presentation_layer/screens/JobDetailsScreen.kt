package com.example.jobapplokal.presentation_layer.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.jobapplokal.presentation_layer.viewModel.AppViewModel
import com.example.jobapplokal.presentation_layer.viewModel.SharedViewModel
import com.example.jobapplokal.ui.theme.AppColor

@Composable
fun JobDetailsScreen(
    viewModel: AppViewModel,
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    val jobsDetails = sharedViewModel.jobsDetails.collectAsState().value

    val verticalScroll= rememberScrollState()
    Column(modifier = Modifier.fillMaxSize().verticalScroll(verticalScroll)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp))
                .background(AppColor)
                .wrapContentHeight()
                .padding(10.dp),
        ) {


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White)
                        .clickable {
                            navController.navigateUp()
                        }
                        .padding(8.dp),
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "", tint = AppColor
                )
                Text(
                    textAlign = TextAlign.Center,
                    text = "Job Details",
                    modifier = Modifier.fillMaxWidth(),
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Black,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.White, fontSize = 20.sp
                )
            }



            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier

                    .fillMaxWidth()
                    .background(Color.White)

                    .background(AppColor)
                    .wrapContentHeight()
                ,
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = jobsDetails?.title.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.White, fontSize = 18.sp
                )

                Text(
                    textAlign = TextAlign.Center,
                    text = jobsDetails?.company_name.toString(),
                    modifier = Modifier.fillMaxWidth(),
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Light,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.White, fontSize = 15.sp
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp), horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                    ) {
                        Icon(
                            tint = Color.White,
                            imageVector = Icons.Default.LocationOn, // Change icon
                            contentDescription = "",
                            modifier = Modifier
                                .size(30.dp)
                                .padding(8.dp)

                        )
                        Text(
                            text = jobsDetails?.primary_details?.Place.toString(),
                            fontSize = 11.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Light,
                            fontFamily = FontFamily.Serif, fontStyle = FontStyle.Normal
                        )

                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                    ) {
                        Icon(
                            tint = Color.White, // Change color
                            imageVector = Icons.Default.Work, // Change icon
                            contentDescription = "",
                            modifier = Modifier
                                .size(30.dp)
                                .padding(8.dp)
                        )
                        Text(
                            text = jobsDetails?.primary_details?.Experience.toString(),
                            fontSize = 11.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Light,
                            fontFamily = FontFamily.Serif, fontStyle = FontStyle.Normal
                        )

                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                    ) {
                        Icon(
                            tint = Color.White, // Change color
                            imageVector = Icons.Default.Money, // Change icon
                            contentDescription = "",
                            modifier = Modifier
                                .size(30.dp)
                                .padding(8.dp)

                        )
                        Text(
                            text = jobsDetails?.primary_details?.Salary.toString(),
                            fontSize = 11.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Light,
                            fontFamily = FontFamily.Serif, fontStyle = FontStyle.Normal
                        )

                    }
                }


            }
        }

        Column(modifier = Modifier.padding( 10.dp)) {

            if (jobsDetails?.other_details!=null){
                Text(
                    textAlign = TextAlign.Justify,
                    text = jobsDetails.other_details.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                    ,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.Black, fontSize = 15.sp
                )

            }
            jobsDetails?.contentV3?.V3?.forEachIndexed { index, v3 ->

                Text(
                    textAlign = TextAlign.Justify,
                    text = v3.field_key,
                    modifier = Modifier
                        .fillMaxWidth(),
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Light,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.Black, fontSize = 15.sp
                )
                Text(
                    textAlign = TextAlign.Justify,
                    text = v3.field_name,
                    modifier = Modifier
                        .fillMaxWidth()
                    ,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Light,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.Black, fontSize = 15.sp
                )
                Text(
                    textAlign = TextAlign.Justify,
                    text = v3.field_value,
                    modifier = Modifier
                        .fillMaxWidth(),
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Light,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.Black, fontSize = 15.sp
                )


            }
        }
    }
}