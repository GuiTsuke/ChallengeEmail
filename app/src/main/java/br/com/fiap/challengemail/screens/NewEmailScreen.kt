package br.com.fiap.challengemail.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.challengemail.model.email.Email


@Composable
fun LoadNewEmailScreen(navController: NavController, idLogin: String?) {



    Scaffold(
        topBar = { NewEmailTopAppBar() }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            NewEmailContent()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewEmailTopAppBar() {
    TopAppBar(
        title = { Text("Novo E-mail") },
        navigationIcon = {
            IconButton(onClick = {

            }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
            }
        },
        actions = {
            IconButton(onClick = { /* Handle action icon press */ }) {
                Icon(Icons.Default.Send, contentDescription = "Enviar")
            }
        }
    )
}

@Composable
fun NewEmailContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        TextField(
            value = "Jorge",
            onValueChange = {},
            label = { Text("Para:") },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Assunto:", fontWeight = FontWeight.Bold) },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth()

        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = "Olá, Jorge!\n\nComo vai você?",
            onValueChange = {},
            label = { Text("Message") },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth(),
            maxLines = 10
        )
    }

}