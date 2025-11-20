package com.emperormoh.reviveapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.emperormoh.reviveapp.presentation.dashboard.DashboardScreen
import com.emperormoh.reviveapp.presentation.login.LoginScreen
import com.emperormoh.reviveapp.presentation.onboarding.OnboardingScreen
import com.emperormoh.reviveapp.presentation.sign_up.SignupScreen
import com.emperormoh.reviveapp.ui.theme.ReViveAppTheme
import com.emperormoh.reviveapp.utils.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ReViveAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()) {
                    AppNavHost()
                }
            }
        }
    }
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.Onboarding
    ) {

        composable(Routes.Onboarding) {
            OnboardingScreen(onContinue = {
                navController.navigate(Routes.Login)
            })
        }


        composable(Routes.Login) {
            LoginScreen(
                onLoginSuccess = { navController.navigate(Routes.Dashboard) },
                onSignUp = { navController.navigate(Routes.Signup) }
            )
        }


        composable(Routes.Signup) {
            SignupScreen(onSignupSuccess = {
                navController.navigate(Routes.Dashboard)
            })
        }


        composable(Routes.Dashboard) {
            DashboardScreen()
        }
    }
}
