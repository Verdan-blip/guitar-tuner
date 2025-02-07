package ru.muztache.feature.signup.domain.mapper

import ru.muztache.feature.signup.domain.entity.UserForm

typealias DataUserForm = ru.muztache.core.data.local.auth.entity.UserForm

fun UserForm.toDataUserForm(): DataUserForm {
    return DataUserForm(email = email, password = password)
}
