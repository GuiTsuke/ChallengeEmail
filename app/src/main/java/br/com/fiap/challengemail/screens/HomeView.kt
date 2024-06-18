package br.com.fiap.challengemail.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.challengemail.R
import br.com.fiap.challengemail.screens.complements.ButtonDefaults
import br.com.fiap.challengemail.screens.complements.TituloBanner


@Composable
fun HomeView(navController: NavController) {
    Box {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
        ){


            Image(
                modifier = Modifier.size(width = 400.dp, height = 400.dp),
                painter = painterResource(id = R.drawable.imagem_home),
                contentDescription = "Imagem de caixa de correio",
            )
            TituloBanner(
                title = "O fim do Gmail ",
                thrsubTitulo = "começa aqui.",
                subTitle = "Intuitivo. Inteligente. Inovador. É toda a eficiência que você precisa, em um só lugar."
            )
                Spacer(Modifier.height(24.dp))
                ButtonDefaults(
                title = "Criar Uma conta",
                onClick = { navController.navigate("signup") })
                TextButton(onClick = { navController.navigate("login") }, Modifier.height(32.dp)) {
                    Text(
                        text =  "Iniciar sessão",
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.Center,
                        color = colorResource(id = R.color.secondary)
                    )
                }

        }
    }
}