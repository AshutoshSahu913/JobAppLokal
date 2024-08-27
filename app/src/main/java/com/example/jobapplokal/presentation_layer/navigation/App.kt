package com.example.jobapplokal.presentation_layer.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.WorkOutline
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import com.example.bottombar.AnimatedBottomBar
import com.example.bottombar.components.BottomBarItem
import com.example.bottombar.model.IndicatorDirection
import com.example.bottombar.model.IndicatorStyle
import com.example.bottombar.model.VisibleItem
import com.example.jobapplokal.presentation_layer.screens.BookmarksScreen
import com.example.jobapplokal.presentation_layer.screens.JobDetailsScreen
import com.example.jobapplokal.presentation_layer.screens.JobScreen
import com.example.jobapplokal.presentation_layer.viewModel.AppViewModel
import com.example.jobapplokal.ui.theme.AppColor

@Composable
fun App(navController: NavHostController) {

    val viewModel:AppViewModel= hiltViewModel()

    var selectedItem by remember { mutableIntStateOf(0) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route
    val shouldShowBottomBar = remember { mutableStateOf(false) }

    LaunchedEffect(currentDestination) {
        shouldShowBottomBar.value = when (currentDestination) {
            NavigationRoutes.JobDetailsScreen::class.qualifiedName -> false
            else -> true
        }
    }

    val BottomNavItem = listOf(
        BottomNavItem(Icons.Default.WorkOutline, "Jobs"),
        BottomNavItem(Icons.Default.Bookmark, "BookMarks"),
    )
    Scaffold(
         Modifier.fillMaxSize(),
        bottomBar = {
            if (shouldShowBottomBar.value) {
                AnimatedBottomBar(
                    selectedItem = selectedItem,
                    itemSize = BottomNavItem.size,
                    modifier = Modifier.padding(bottom = 10.dp),
                    indicatorShape = CircleShape,
                    containerColor = Color.White,
                    indicatorColor = AppColor,
                    contentColor = Color.White,
                    indicatorDirection = IndicatorDirection.TOP,
                    indicatorStyle = IndicatorStyle.FILLED
                ) {
                    BottomNavItem.forEachIndexed { index, navigationItem ->
                        BottomBarItem(
                            selected = selectedItem == index,
                            onClick = {
                                selectedItem = index
                                when (index) {
                                    0 -> navController.navigate(NavigationRoutes.JobScreen)
                                    1 -> navController.navigate(NavigationRoutes.BookmarkScreen)
                                }
                            },
                            imageVector = navigationItem.icons,
                            label = navigationItem.title,
                            containerColor = Color.Transparent,
                            iconColor = Color.White,
                            visibleItem = VisibleItem.ICON
                        )
                    }
                }
            }
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = if (shouldShowBottomBar.value) innerPadding.calculateBottomPadding() else 0.dp)
        ) {


            NavHost(
                navController = navController,
                startDestination = SubNavigation.BottomNavScreen
            ) {
                navigation<SubNavigation.BottomNavScreen>(startDestination = NavigationRoutes.JobScreen) {

                    composable<NavigationRoutes.JobScreen> {
                        JobScreen(viewModel,navController)
                    }

                    composable<NavigationRoutes.BookmarkScreen> {
                        BookmarksScreen(viewModel,navController)
                    }


                }

                navigation<SubNavigation.OtherScreen>(startDestination = NavigationRoutes.JobDetailsScreen) {
                    composable<NavigationRoutes.JobDetailsScreen> {
                        JobDetailsScreen(viewModel,navController)
                    }
                }
            }

        }

    }
}

data class BottomNavItem(
    val icons: ImageVector,
    val title: String
)