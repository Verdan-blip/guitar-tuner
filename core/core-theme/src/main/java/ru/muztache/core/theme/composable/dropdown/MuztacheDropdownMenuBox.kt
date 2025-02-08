package ru.muztache.core.theme.composable.dropdown

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.muztache.core.theme.MuztacheTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MuztacheDropdownMenuBox(
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onDismissItem: () -> Unit,
    onItemClick: (String) -> Unit,
    selectedText: String,
    label: String,
    options: List<String>,
    modifier: Modifier = Modifier
) {
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = onExpandedChange,
        modifier = modifier
    ) {
        TextField(
            readOnly = true,
            value = selectedText,
            onValueChange = { },
            label = {
                Text(
                    text = label,
                    style = MuztacheTheme.typography.bodyMedium,
                    color = MuztacheTheme.colors.textSecondary
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = null
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                focusedTextColor = MuztacheTheme.colors.textPrimary,
                disabledTextColor = MuztacheTheme.colors.textUnselected
            ),
            modifier = Modifier
                .menuAnchor()
                .clickable {
                    onExpandedChange(false)
                }
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = onDismissItem
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        onItemClick(selectionOption)
                    },
                    text = {
                        Text(
                            text = selectionOption,
                            style = MuztacheTheme.typography.bodyMedium
                        )
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun MuztacheDropdownMenuBoxPreview() {
    MuztacheTheme {
        val expanded = remember {
            mutableStateOf(false)
        }
        val selected = remember {
            mutableStateOf("Opt1")
        }
        MuztacheDropdownMenuBox(
            expanded = expanded.value,
            onExpandedChange = { flag ->
                expanded.value = flag
            },
            onDismissItem = {  },
            onItemClick = {  },
            selectedText = selected.value,
            label = "Categories",
            options = listOf("Opt1", "Opt2", "Opt3")
        )
    }
}
