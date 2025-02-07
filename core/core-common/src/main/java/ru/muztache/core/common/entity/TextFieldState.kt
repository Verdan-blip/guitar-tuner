package ru.muztache.core.common.entity

sealed class TextFieldState(val value: String) {

    class Idle(value: String) : TextFieldState(value)

    class Error(value: String, val message: String) : TextFieldState(value)

    class Disabled(value: String) : TextFieldState(value)
}