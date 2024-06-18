package br.com.fiap.challengemail.screens.complements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.fiap.challengemail.R

@Composable
fun TituloBanner(title : String, subTitle : String = "", thrsubTitulo: String = "") {

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(44.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                color = colorResource(R.color.primary)
            )
            Text(
                text = thrsubTitulo,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                color = colorResource(R.color.secondary)
            )
            Text(
                text = subTitle,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                color = colorResource(R.color.primary)
            )
            Spacer(Modifier.height(8.dp))
        }
    }
