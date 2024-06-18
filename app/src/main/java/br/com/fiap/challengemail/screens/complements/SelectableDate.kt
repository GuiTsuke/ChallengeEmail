package br.com.fiap.challengeemail.modules.complements

import br.com.fiap.challengeemail.R
import br.com.fiap.challengeemail.ui.theme.Typography
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Calendar
import java.util.Date


@Composable
fun SelectableDate(placeholder: String, date: String, setDate: (String) -> Unit) {

    val calendar = Calendar.getInstance()
    val year: Int = calendar.get(Calendar.YEAR)
    val month: Int = calendar.get(Calendar.MONTH)
    val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val context = LocalContext.current
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            setDate("$dayOfMonth/$month/$year")
        }, year, month, day
    )


    Button(
        onClick = { datePickerDialog.show() },
        modifier = Modifier
            .width(340.dp)
            .padding(horizontal = 0.dp)
            .border(width = 1.dp, color = colorResource(id = R.color.black), shape = RoundedCornerShape(4.dp)),
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.white))
    ) {
        Text(
            text = if (date.isEmpty()) placeholder else date,
            style = Typography.bodyLarge,
            color = colorResource(R.color.black),
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(vertical = 10.dp).fillMaxWidth()
        )
    }


}

