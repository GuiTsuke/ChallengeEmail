package br.com.fiap.challengemail.screens.complements

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.fiap.challengemail.R

@Composable
fun ButtonDefaults(title: String, onClick: () -> Unit) {
    Button(
        onClick = onClick, Modifier.width(300.dp), colors = ButtonDefaults.buttonColors(containerColor = colorResource(
            id = R.color.primary
        )
        )) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = colorResource(id = R.color.white)
        )
    }
}