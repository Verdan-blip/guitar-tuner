package ru.muztache.core.theme.composable.field

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import ru.muztache.core.common.entity.TextFieldState
import ru.muztache.core.theme.MuztacheTheme
import ru.muztache.core.theme.composable.surface.MuztacheSurface

@Composable
fun MuztacheInvincibleTextField(
    modifier: Modifier = Modifier,
    textField: TextFieldState,
    placeholder: String,
    onValueChange: (String) -> Unit,
    trailingIcon: (@Composable () -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    TextField(
        value = textField.value,
        onValueChange = onValueChange,
        trailingIcon = trailingIcon,
        textStyle = MuztacheTheme.typography.titleMedium.copy(
            textAlign = TextAlign.Center
        ),
        placeholder = {
            Text(
                text = placeholder,
                style = MuztacheTheme.typography.titleMedium,
                color = MuztacheTheme.colors.textSecondary
            )
        },
        visualTransformation = visualTransformation,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            focusedTextColor = MuztacheTheme.colors.textPrimary,
            unfocusedIndicatorColor = MuztacheTheme.colors.textPrimary,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent
        ),
        modifier = modifier
    )
}

@Preview
@Composable
private fun MuztacheInvincibleFieldPreview() {
    MuztacheTheme {
        MuztacheSurface {
            MuztacheInvincibleTextField(
                modifier = Modifier,
                placeholder = "",
                textField = TextFieldState.Idle("Something"),
                onValueChange = { }
            )
        }
    }
}
