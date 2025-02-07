package ru.muztache.feature.profile.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import ru.muztache.core.common.entity.TextFieldState
import ru.muztache.core.theme.MuztacheTheme
import ru.muztache.core.theme.composable.field.MuztacheInvincibleTextField
import ru.muztache.core.theme.composable.progress.MuztacheProgressIndicator
import ru.muztache.core.theme.composable.surface.MuztacheSurface
import ru.muztache.feature.profile.R
import ru.muztache.feature.profile.ui.entity.UserProfileModel

@Composable
internal fun AuthorizedScreen(
    userProfileEntity: UserProfileModel,
    onNameChange: (String) -> Unit,
    onSubmitChangedName: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 16.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = MuztacheTheme.colors.surface
            ),
            shape = RoundedCornerShape(
                bottomStart = MuztacheTheme.corners.extraLarge + MuztacheTheme.paddings.large,
                bottomEnd = MuztacheTheme.corners.extraLarge + MuztacheTheme.paddings.large
            )
        ) {
            Text(
                text = stringResource(R.string.title_profile),
                style = MuztacheTheme.typography.displayMedium,
                color = MuztacheTheme.colors.textPrimary,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = MuztacheTheme.paddings.large)
            )
            SubcomposeAsyncImage(
                model = userProfileEntity.photoUri,
                contentDescription = null,
                loading = {
                    MuztacheProgressIndicator()
                },
                error = {
                    Image(
                        painter = painterResource(ru.muztache.core.theme.R.drawable.photo_placeholder),
                        contentDescription = null
                    )
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = MuztacheTheme.paddings.large)
                    .size(96.dp)
                    .clip(CircleShape)
            )
            Text(
                text = userProfileEntity.email,
                style = MuztacheTheme.typography.bodyMedium,
                color = MuztacheTheme.colors.textPrimary,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = MuztacheTheme.paddings.medium)
            )
            MuztacheInvincibleTextField(
                textField = userProfileEntity.name,
                placeholder = stringResource(R.string.define_name),
                onValueChange = onNameChange,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = null,
                        tint = if (userProfileEntity.name !is TextFieldState.Error)
                            MuztacheTheme.colors.primary
                        else
                            MuztacheTheme.colors.neutral,
                        modifier = Modifier
                            .clickable { onSubmitChangedName() }
                    )
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = MuztacheTheme.paddings.normal)
                    .padding(bottom = MuztacheTheme.paddings.large)
            )
        }
    }
}

@Preview
@Composable
private fun AuthorizedScreenPreview() {
    MuztacheTheme {
        MuztacheSurface {
            AuthorizedScreen(
                userProfileEntity = UserProfileModel(
                    id = "123456",
                    email = "user@mail.ru",
                    name = TextFieldState.Idle(""),
                    photoUri = "String"
                ),
                onNameChange = { },
                onSubmitChangedName = { },
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}