package br.com.fiap.challengemail.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.challengemail.model.email.Email
import br.com.fiap.challengemail.screens.complements.AppInfo
import br.com.fiap.challengemail.screens.complements.ConfirmationDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadHomeScreen(title: String, navController: NavController) {
    EmailListScreen(
            modifier = Modifier
                .fillMaxSize(),
            navController
        )
}

@Composable
fun EmailListScreen(modifier: Modifier = Modifier, navController: NavController) {

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

    val emails = listOf(
        Email(assunto = "Liberação de acesso ao dash", corpo = "Acesso liberado na geral, Lucas! Qualquer coisa chama, tamo junto 💪", remetente = "Felipe, Luis", dataEnvio = "15:00"),
        Email(assunto = "Precisamos falar da nova logo.", corpo = "Oi, Lucas. Cara, a nova logo da empresa tá horrível. Se liga nessa versão aqui...", remetente = "Josh Senger", dataEnvio = "08:32"),
        // Adicione mais emails aqui
    )
    Scaffold(
        topBar = { EmailTopAppBar() },
        bottomBar = { EmailBottomAppBar() },
        floatingActionButton = { EmailFloatingActionButton() }
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
fun EmailBottomAppBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
    ) {
        BottomAppBar(
            contentColor = Color.Transparent, // Definindo como transparente para que o Box com o background seja visível
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                BottomAppBarIcon(Icons.Default.Home, "Home", Modifier.clickable(onClick = { /* Handle home icon press */ }))
                BottomAppBarIcon(Icons.Default.Search, "Search", Modifier.clickable(onClick = { /* Handle search icon press */ }))
                BottomAppBarIcon(Icons.Default.Settings, "Settings", Modifier.clickable(onClick = { /* Handle settings icon press */ }))
            }
        }
    }
}

@Composable
fun BottomAppBarIcon(
    icon: ImageVector,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = { /* Handle icon click */ },
        modifier = modifier.padding(8.dp)
    ) {
        Icon(icon, contentDescription)
    }
}


@Composable
fun EmailFloatingActionButton() {
    FloatingActionButton(
        onClick = { /* Handle FAB click */ },
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        Icon(Icons.Default.Add, contentDescription = "Add")
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
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier.size(40.dp).background(MaterialTheme.colorScheme.primary, CircleShape)
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