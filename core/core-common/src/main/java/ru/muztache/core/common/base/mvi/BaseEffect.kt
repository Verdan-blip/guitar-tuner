package ru.muztache.core.common.base.mvi

sealed interface BaseEffect {

    data class ShowSnackBar(val message: String) : BaseEffect

    data class NavigateTo(val route: Any) : BaseEffect
}
