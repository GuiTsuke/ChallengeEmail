package br.com.fiap.challengeemail.modules.complements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import br.com.fiap.challengeemail.R
import br.com.fiap.challengeemail.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextoFiel(
    title: String,
    keyboardType: KeyboardType,
    onValueChange: (String) -> Unit,
    value: String,
    error: String = ""
) {
    Column {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            colors = TextFieldDefaults.textFieldColors(
                textColor = colorResource(R.color.primary),
                containerColor = colorResource(R.color.white),
                focusedIndicatorColor = colorResource(R.color.primary)
            ),
            shape = RoundedCornerShape(4.dp),
            placeholder = {
                Text(
                    modifier = Modifier.padding(start = 6.dp),
                    text = title,
                    style = Typography.bodyLarge    ,
                    color = colorResource(R.color.primary)
                )
            },
            isError = value.isNotEmpty() && error != "",
            modifier = Modifier.width(340.dp),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = if (keyboardType == KeyboardType.Password) PasswordVisualTransformation() else VisualTransformation.None
        )
        if (error != "") {
            Text(
                text = error, style = Typography.labelSmall,
                color = colorResource(id = R.color.secondery),
            )
        }
    }

}