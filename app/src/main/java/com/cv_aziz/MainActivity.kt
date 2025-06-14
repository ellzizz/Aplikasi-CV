@file:Suppress("DEPRECATION")

package com.cv_aziz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cv_aziz.tampilan.ActivityScreen
import com.cv_aziz.tampilan.EducationScreen
import com.cv_aziz.tampilan.HomeScreen
import com.cv_aziz.tampilan.LainnyaScreen
import com.cv_aziz.tampilan.MusikScreen
import com.cv_aziz.tampilan.PlayMusicScreen
import com.cv_aziz.tampilan.SkillScreen
import com.cv_aziz.tampilan.TentangScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import java.net.URLDecoder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PortfolioApp()
        }
    }
}

@Composable
fun PortfolioApp() {
    val navController = rememberNavController()
    val items = listOf(
        BottomNavItem("kegiatan", Icons.Filled.Schedule),
        BottomNavItem("skill", Icons.Filled.Person),
        BottomNavItem("home", Icons.Filled.Home),
        BottomNavItem("pendidikan", Icons.Filled.School),
        BottomNavItem("lainnya", Icons.Filled.Menu)
    )

    var selectedIndex by remember { mutableIntStateOf(2) } // Default home in center
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title.replaceFirstChar { it.uppercase() }) },
                        selected = currentRoute?.startsWith(item.title) == true,
                        onClick = {
                            selectedIndex = index
                            navController.navigate(item.title) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        },
        content = { innerPadding ->
            AnimatedNavigationHost(
                navController = navController,
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            )
        }
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedNavigationHost(navController: NavHostController, modifier: Modifier = Modifier) {
    val screens = listOf("kegiatan", "skill", "home", "pendidikan", "lainnya")

    AnimatedNavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier,
        enterTransition = { getEnterTransition(screens, initialState, targetState) },
        exitTransition = { getExitTransition(screens, initialState, targetState) },
    ) {
        composable("home") { HomeScreen() }
        composable("skill") { SkillScreen() }
        composable("kegiatan") { ActivityScreen() }
        composable("pendidikan") { EducationScreen() }
        composable("lainnya") { LainnyaScreen(navController) }
        composable("musik") { MusikScreen(navController) }
        composable("tentang") { TentangScreen() }

        // Navigasi ke PlayMusicScreen
        composable("playmusic/{judul}") { backStackEntry ->
            val encodedTitle = backStackEntry.arguments?.getString("judul") ?: ""
            val judul = URLDecoder.decode(encodedTitle, "UTF-8")
            PlayMusicScreen(judulLagu = judul)
        }
    }
}

fun getEnterTransition(
    screens: List<String>,
    initial: NavBackStackEntry,
    target: NavBackStackEntry
): EnterTransition {
    val fromIndex = screens.indexOf(initial.destination.route)
    val toIndex = screens.indexOf(target.destination.route)
    return if (toIndex > fromIndex) {
        slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(300)) +
                fadeIn(animationSpec = tween(300))
    } else {
        slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(300)) +
                fadeIn(animationSpec = tween(300))
    }
}

fun getExitTransition(
    screens: List<String>,
    initial: NavBackStackEntry,
    target: NavBackStackEntry
): ExitTransition {
    val fromIndex = screens.indexOf(initial.destination.route)
    val toIndex = screens.indexOf(target.destination.route)
    return if (toIndex > fromIndex) {
        slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(300)) +
                fadeOut(animationSpec = tween(300))
    } else {
        slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(300)) +
                fadeOut(animationSpec = tween(300))
    }
}

data class BottomNavItem(
    val title: String,
    val icon: ImageVector
)
