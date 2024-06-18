package br.com.fiap.challengemail.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.challengemail.repository.EmailRepository

@Composable
fun LoadViewEmailScreen(navController: NavController, id: String?) {



    Scaffold(
        topBar = { EmailDetailTopAppBar(navController) }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            EmailDetailContent(navController, id)
        }
    }


}
@Composable
fun EmailDetailContent(navController: NavController, id: String?) {
    val context = LocalContext.current

    val emailRepository = EmailRepository(context)
    val email = emailRepository.getEmailById(id!!.toInt())

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(MaterialTheme.colorScheme.primary, CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(email.assunto, fontSize = 20.sp, color = Color.Black)
                Text(email.dataEnvio, fontSize = 14.sp, color = Color.Gray)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(email.corpo, fontSize = 20.sp, color = Color.Black)

        Spacer(modifier = Modifier.height(8.dp))
        ClickableText(
            text = AnnotatedString("proposta-nova-logo.png", spanStyle = SpanStyle(color = Color.Blue)),
            onClick = { /* handle click */ }
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailDetailTopAppBar(navController: NavController) {
    TopAppBar(
        title = { Text("Detalhes do E-mail") },
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
            }
        }
    )

}
