package br.com.fiap.challengemail.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.challengemail.model.email.Email
import br.com.fiap.challengemail.repository.EmailRepository
import br.com.fiap.challengemail.repository.UsuarioRepository
import br.com.fiap.challengemail.screens.complements.ConfirmationDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadHomeScreen(navController: NavController, idLogin: String?) {
    EmailListScreen(
        navController,
        idLogin?:""
    )
}

@Composable
fun EmailListScreen(navController: NavController, idLogin: String) {

    var showDialogBackLogin by remember { mutableStateOf(false) }

    if (showDialogBackLogin) {
        ConfirmationDialog(
            onConfirm = {
                showDialogBackLogin = false
                navController.popBackStack("login", inclusive = false)
            },
            onDismiss = {
                showDialogBackLogin = false
            }
        )
    }

    BackHandler(onBack = {
        showDialogBackLogin = true
    })
    val context = LocalContext.current
    val emailRepository = EmailRepository(context)
    val usuarioRepository = UsuarioRepository(context)

    val user = usuarioRepository.getUserById(idLogin.toInt())

    val emails = emailRepository.getAllEmails(user.email)

    Scaffold(
        topBar = { EmailTopAppBar() },
        floatingActionButton = {
            EmailFloatingActionButton()

        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            EmailFilterRow()
            EmailList(emails = emails)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailTopAppBar() {
    TopAppBar(
        title = { Text("Caixa de entrada") },
        navigationIcon = {
            IconButton(onClick = { /* Handle navigation icon press */ }) {
                Icon(Icons.Default.Menu, contentDescription = "Menu")
            }
        }
    )
}

@Composable
fun EmailFloatingActionButton() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(50.dp)
                .height(72.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(50.dp)
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                FloatingActionButton(
                    onClick = {
                        // Ação quando clicar na opção 1
                    },
                    shape = CircleShape,
                    containerColor = Color.Transparent){
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Opção 1"
                    )
                }

                FloatingActionButton(
                    onClick = {
                        // Ação quando clicar na opção 1
                    },
                    shape = CircleShape,
                    containerColor = Color.Transparent){
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Opção 1"
                    )
                }

                FloatingActionButton(
                    onClick = {
                        // Ação quando clicar na opção 1
                    },
                    shape = CircleShape,
                    containerColor = Color.Transparent){
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Opção 1"
                    )
                }


            }
        }
    }

@Composable
fun EmailFilterRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Tudo", fontWeight = FontWeight.Bold)
        Text(text = "Lidos")
        Text(text = "Não lidos")
        Text(text = "Enviados")
        Text(text = "Deletado")
    }
}

@Composable
fun EmailList(emails: List<Email>) {
    LazyColumn {
        items(emails) { email ->
            EmailItem(email)
        }
    }
}

@Composable
fun EmailItem(email: Email) {
    var anexo by remember { mutableStateOf("") }
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(MaterialTheme.colorScheme.primary, CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = email.remetente, fontWeight = FontWeight.Bold)
                Text(text = email.assunto)
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(text = email.dataEnvio, color = Color.Gray)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = email.corpo)
    }
}

@Preview
@Composable
private fun Botoes() {
    EmailFloatingActionButton()
}