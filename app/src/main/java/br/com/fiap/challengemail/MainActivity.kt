package br.com.fiap.challengemail

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.challengemail.screens.LoadHomeScreen
import br.com.fiap.challengemail.screens.LoadLoginScreen
import br.com.fiap.challengemail.screens.LoadSignupScreen
import br.com.fiap.challengemail.ui.theme.ChallengeEmailTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChallengeEmailTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "login"
                    ) {
                        composable("login") {
                            LoadLoginScreen("Login", navController)
                        }
                        composable("signup") {
                            LoadSignupScreen("Signup", navController)
                        }
                        composable("home/{id}") {
                            val id = it.arguments?.getString("id")
                            LoadHomeScreen(navController, id)
                        }
                    }

                }
            }
        }
    }
}


