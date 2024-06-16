package br.com.fiap.challengemail.screens

import android.content.Context
import android.util.Patterns.EMAIL_ADDRESS
import android.widget.Toast
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.challengemail.model.usuario.Usuario
import br.com.fiap.challengemail.repository.AuthRepository
import br.com.fiap.challengemail.repository.UsuarioRepository
import br.com.fiap.challengemail.screens.complements.AppInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadLoginScreen(title: String, navController: NavController) {
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
        LoginScreen(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            navController
        )
    }
}

@Composable
fun LoginScreen(modifier: Modifier = Modifier, navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var userMsgError by remember { mutableStateOf("") }
    var passMsgError by remember { mutableStateOf("") }
    var isUserError by remember { mutableStateOf(false) }
    var isPasswordError by remember { mutableStateOf(false) }
    var isLoginButtonEnabled by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val authRepository = AuthRepository(context)

    var loginResult by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current

    popularDados(context);

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Card(
            modifier = Modifier
                .size(width = 100.dp, height = 100.dp)
                .align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),

            ) {

        }
        Spacer(modifier = Modifier.height(32.dp))
        Column() {
            Text(text = "Login")
            Text(text = "Faça login usando admin@admin.com e senha 123456")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column() {
            OutlinedTextField(
                value = username,
                onValueChange = {
                    username = it
                    userMsgError = if (isValidEmail(username)) "" else "Email inválido"
                    isUserError = !isValidEmail(username)
                    isLoginButtonEnabled = !isUserError && !isPasswordError
                },
                label = { Text("Usuário") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = userMsgError, color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(2.dp))
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    passMsgError = if (isValidPassword(password)) "" else "Senha inválida"
                    isPasswordError = !isValidPassword(password)
                    isLoginButtonEnabled = !isUserError && !isPasswordError
                },
                label = { Text("Senha") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.clearFocus()
                    }
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = passMsgError, color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(24.dp))
        }
        Column() {
            Button(
                onClick = {
                    loginResult =
                        authRepository.autenticarUsuario(email = username, senha = password)
                    if (loginResult) {
                        Toast.makeText(context, "Login Efetuado com sucesso", Toast.LENGTH_SHORT)
                            .show()
                        navController.navigate("Home")
                    } else {
                        Toast.makeText(context, "Usuário e senha inválidos", Toast.LENGTH_SHORT)
                            .show()
                    }
                },
                enabled = isLoginButtonEnabled
            ) {
                Text("Fazer Login")
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "Botão para fazer Login"
                )
            }
            Row() {
                Text("Ainda não possui conta? ")
                Text(
                    "Cadastre-se",
                    modifier = if (false) {
                        Modifier.clickable {
                            navController.navigate("signup")
                        }
                    } else {
                        Modifier
                    },
                    color = if (false) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    fontWeight = FontWeight.ExtraBold,

                )
            }
        }

    }
}


fun isValidEmail(email: String): Boolean {
    return EMAIL_ADDRESS.matcher(email).matches()
}

fun isValidPassword(password: String): Boolean {
    return password.length >= 6  // Mínimo de 6 caracteres
}

fun popularDados(context : Context){
    val usuarioRepository = UsuarioRepository(context)
    if (usuarioRepository.getUserByEmail("admin@admin.com").id <= 0){
        val usuarioAdmin = Usuario(
            name = "Admin",
            email = "admin@admin.com",
            password = "123456"
        )

        usuarioRepository.createUser(usuarioAdmin)
    }


}