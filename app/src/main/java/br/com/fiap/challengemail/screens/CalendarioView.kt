package br.com.fiap.challengemail.screens


import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.challengeemail.modules.complements.BackBtn
import br.com.fiap.challengeemail.modules.complements.SelectableDate
import br.com.fiap.challengeemail.modules.complements.TextoFiel
import br.com.fiap.challengemail.R
import br.com.fiap.challengemail.screens.complements.ButtonDefaults
import br.com.fiap.challengemail.screens.complements.DateFormat

fun EventoCalendario(context: Context, initialDate: String, finalDate: String, title: String) {
    val intent = Intent(Intent.ACTION_EDIT)
    val calendarioinicial = DateFormat(initialDate)
    val calendariofinal = DateFormat(finalDate)
    calendarioinicial?.let {
        intent.putExtra("inícioHora", it.timeInMillis)
    }
    calendariofinal?.let {
        intent.putExtra("fimHora", it.timeInMillis)
    }
    intent.type = "vnd.android.cursor.item/event"
    intent.putExtra("allDay", true)
    intent.putExtra("title", title)
    context.startActivity(intent)
}

@Composable
fun CalendarioView(navController: NavController, viewModel: CalendarioViewModel) {

    val titulo by viewModel.title.observeAsState(initial = "")
    val formularioErro by viewModel.formError.observeAsState(initial = "")
    val dataInicial by viewModel.initialDate.observeAsState(initial = "")
    val dataFinal by viewModel.finalDate.observeAsState(initial = "")

    val context = LocalContext.current

    Box {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            BackBtn(navController)
            //TituloBanner(title = "Criação de evento")
            Spacer(modifier = Modifier.height(32.dp))
            TextoFiel(
                "Evento",
                keyboardType = KeyboardType.Email,
                onValueChange = { viewModel.setEvent(it) },
                value = titulo,
            )
            Spacer(modifier = Modifier.height(16.dp))
            SelectableDate(
                "Data inicial",
                setDate = { viewModel.setInitialDate(it) },
                date = dataInicial
            )
            Spacer(modifier = Modifier.height(16.dp))
            SelectableDate(
                "Data final",
                setDate = { viewModel.setFinalDate(it) },
                date = dataFinal
            )
            Spacer(modifier = Modifier.height(32.dp))
            ButtonDefaults(title = "Agendar") {
                try {

                    Log.i(null, dataInicial)
                    Log.i(null, dataFinal,)
                    if(titulo != "" && dataInicial != "" && dataFinal != ""){
                        viewModel.setFormError("")
                        EventoCalendario(context, dataInicial, dataFinal, titulo)
                    } else {
                        viewModel.setFormError("Preencha os campos")
                    }
                } catch (e: Exception) {
                    viewModel.setFormError(e.message!!)
                    Log.i("Error", e.message!!)
                }
            }
            Text(
                text = formularioErro,
                style = MaterialTheme.typography.labelSmall,
                color = colorResource(id = R.color.secondary),
                textAlign = TextAlign.Center
            )
        }
    }
}