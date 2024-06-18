package br.com.fiap.challengemail.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
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
            EmailFloatingActionButton(navController, idLogin)

        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            EmailFilterRow(navController, idLogin)
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
fun EmailFloatingActionButton(navController: NavController, idLogin: String) {
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

                    },
                    shape = CircleShape,
                    containerColor = Color.Transparent,
                    elevation = FloatingActionButtonDefaults.elevation(0.dp)){
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Opção 1",
                        modifier = Modifier.size(48.dp)
                    )
                }

                FloatingActionButton(
                    onClick = {
                        // Ação quando clicar na opção 1
                    },
                    shape = CircleShape,
                    containerColor = Color.Transparent,
                    elevation = FloatingActionButtonDefaults.elevation(0.dp)){
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Opção 2",
                        modifier = Modifier.size(48.dp)
                    )
                }

                FloatingActionButton(
                    onClick = {
                        navController.navigate("calendar")
                    },
                    shape = CircleShape,
                    containerColor = Color.Transparent,
                    elevation = FloatingActionButtonDefaults.elevation(0.dp)){
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Opção 3",
                        modifier = Modifier.size(48.dp)
                    )
                }


            }
        }
    }

@Composable
fun EmailFilterRow(navController: NavController, idLogin: String) {
    val context = LocalContext.current
    val emailRepository = EmailRepository(context)
    val usuarioRepository = UsuarioRepository(context)

    val user = usuarioRepository.getUserById(idLogin.toInt())

    var emails by remember{mutableStateOf(emailRepository.getAllEmails(user.email))}

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Tudo", modifier = Modifier.clickable(
            onClick = {
                emails = emailRepository.getAllEmails(user.email)
            }
        ))
        Text(text = "Enviados", modifier = Modifier.clickable(
            onClick = {
                emails = emailRepository.getAllEmails(user.email).filter { it.remetente == "admin@admin.com" } }
        ))
        Text(text = "Deletado", modifier = Modifier.clickable(
            onClick = { emails = emailRepository.getAllEmails(user.email).filter { it.isDeleted } }
        ))
        Text(text = "Tag 1", modifier = Modifier.clickable(
            onClick = { emails = emailRepository.getAllEmails(user.email).filter { it.tags == "Tag 1" } }
        ))
        Text(text = "Tag 2", modifier = Modifier.clickable(
            onClick = { emails = emailRepository.getAllEmails(user.email).filter { it.tags == "Tag 2" } }
        ))
    }

    EmailList(emails = emails, navController)
}

@Composable
fun EmailList(emails: List<Email>, navController: NavController) {
    LazyColumn {
        items(emails) { email ->
            EmailItem(email, navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailItem(email: Email, navController: NavController) {
    Card(
        onClick = {
            navController.navigate("EmailDetail/${email.id}")
        },
        modifier = Modifier.padding(8.dp)
    ){
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


}