package ru.muztache.feature.signin.domain.mapper

import ru.muztache.feature.signin.domain.entity.UserForm

typealias DataUserForm = ru.muztache.core.data.local.auth.entity.UserForm

fun UserForm.toDataUserForm(): DataUserForm {
    return DataUserForm(email = email, password = password)
}