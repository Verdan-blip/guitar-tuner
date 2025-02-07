package ru.muztache.core.theme.composable.field

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import ru.muztache.core.common.entity.TextFieldState
import ru.muztache.core.theme.MuztacheTheme

@Composable
fun MuztacheTextField(
    modifier: Modifier = Modifier,
    textFieldState: TextFieldState,
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    placeholder: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        OutlinedTextField(
            value = textFieldState.value,
            onValueChange = onValueChange,
            visualTransformation = visualTransformation,
            isError = textFieldState is TextFieldState.Error,
            shape = RoundedCornerShape(MuztacheTheme.corners.extraLarge),
            textStyle = MuztacheTheme.typography.bodyMedium,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = MuztacheTheme.colors.primary,
                unfocusedIndicatorColor = MuztacheTheme.colors.neutral
            ),
            placeholder = {
                Text(
                    text = placeholder,
                    style = MuztacheTheme.typography.bodyMedium
                )
            },
            supportingText = if (textFieldState is TextFieldState.Error) {
                @Composable {
                    Text(
                        text = textFieldState.message,
                        style = MuztacheTheme.typography.labelSmall
                    )
                }
            } else {
                null
            }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun MuztacheTextFieldPreview() {
    MuztacheTheme {
        MuztacheTextField(
            textFieldState = TextFieldState.Idle(
                "",
            ),
            onValueChange = { },
            placeholder = "Почта",
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}
