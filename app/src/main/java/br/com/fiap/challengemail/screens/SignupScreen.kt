package br.com.fiap.challengemail.screens

import android.os.Build
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.challengemail.model.usuario.Usuario
import br.com.fiap.challengemail.repository.UsuarioRepository
import br.com.fiap.challengemail.screens.complements.ConfirmationDialog
import br.com.fiap.challengemail.screens.complements.AppInfo
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadSignupScreen(title: String, navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = title)
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primary

            ) {
                AppInfo()
            }
        }
    ) { innerPadding ->
        SignupScreen(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            navController
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SignupScreen(modifier: Modifier, navController: NavController) {

    val context = LocalContext.current
    val usuarioRepository = UsuarioRepository(context)

    var nomeState by remember { mutableStateOf("") }
    var emailState by remember { mutableStateOf("") }
    var senhaState by remember { mutableStateOf("") }
    var confirmedSenhaState by remember { mutableStateOf("") }

    var showDialogBackLogin by remember { mutableStateOf(false) }

    if (showDialogBackLogin) {
        ConfirmationDialog(
            onConfirm = {
                showDialogBackLogin = false
                navController.popBackStack()
            },
            onDismiss = {
                showDialogBackLogin = false
            }
        )
    }

    BackHandler(onBack = {
        showDialogBackLogin = true
    })


    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .size(width = 100.dp, height = 100.dp)
                .align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),

            ) {

        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = nomeState,
            onValueChange = { nomeState = it },
            label = { Text("Nome") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        OutlinedTextField(
            value = emailState,
            onValueChange = { emailState = it },
            label = { Text("E-mail") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        OutlinedTextField(
            value = senhaState,
            onValueChange = { senhaState = it },
            label = { Text("Senha") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        OutlinedTextField(
            value = confirmedSenhaState,
            onValueChange = { confirmedSenhaState = it },
            label = { Text("Confirmação de Senha") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Row {
            Button(
                onClick = {
                    val usuario = Usuario(
                        name = nomeState,
                        email = emailState,
                        password = senhaState,
                        dataCadastro = LocalDate.now().toString()
                    )
                    Toast.makeText(context, usuarioRepository.createUser(usuario), Toast.LENGTH_SHORT).show()
                    navController.popBackStack("login", inclusive = false)

                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Signup")
            }
            Button(
                onClick = {
                    showDialogBackLogin = true
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Voltar")
            }
        }

    }
}