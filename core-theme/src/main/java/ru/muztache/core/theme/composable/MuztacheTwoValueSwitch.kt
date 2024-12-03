package ru.muztache.core.theme.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.muztache.core.theme.MuztacheTheme

enum class SelectedLabel { Label1, Label2 }

@Composable
fun MuztacheTwoValueSwitch(
    modifier: Modifier = Modifier,
    label1: String,
    label2: String,
    selectedLabel: SelectedLabel = SelectedLabel.Label1,
    onLabelCheckedChange: (SelectedLabel) -> Unit,
    enabled: Boolean = true
) {
    val selectedLabelState = remember { mutableStateOf(selectedLabel) }
    Row(
        modifier = Modifier
            .background(
                color = MuztacheTheme.colors.primary,
                shape = RoundedCornerShape(MuztacheTheme.corners.medium)
            )
            .padding(
                all = MuztacheTheme.paddings.extraSmall
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.apply {
                if (selectedLabelState.value == SelectedLabel.Label1) {
                    background(
                        color = MuztacheTheme.colors.surface,
                        shape = RoundedCornerShape(MuztacheTheme.corners.medium)
                    )
                }
                clickable {
                    selectedLabelState.value = SelectedLabel.Label1
                }
                padding(
                    vertical = MuztacheTheme.paddings.small,
                    horizontal = MuztacheTheme.paddings.normal
                )
            }
        ) {
            Text(
                text = label1,
                color = MuztacheTheme.colors.onSurface
            )
        }
        Box(
            modifier = Modifier.apply {
                clickable {
                    selectedLabelState.value = SelectedLabel.Label2
                }
                if (selectedLabelState.value == SelectedLabel.Label2) {
                    background(
                        color = MuztacheTheme.colors.surface,
                        shape = RoundedCornerShape(MuztacheTheme.corners.medium)
                    )
                }
                padding(
                    start = MuztacheTheme.paddings.normal,
                    end = MuztacheTheme.paddings.medium
                )
            }
        ) {
            Text(
                text = label2,
                color = MuztacheTheme.colors.onPrimary
            )
        }
    }
}

@Preview
@Composable
private fun MuztacheTwoValueSwitchPreview() {
    MuztacheTheme {
        MuztacheTwoValueSwitch(
            label1 = "Auto",
            label2 = "Manually",
            selectedLabel = SelectedLabel.Label1,
            onLabelCheckedChange = { }
        )
    }
}