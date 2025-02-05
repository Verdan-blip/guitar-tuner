package ru.muztache.core.data.local.chords.entity

sealed interface FingeringType {

    data object Skip : FingeringType

    data object Open : FingeringType

    data class Use(val fret: Int) : FingeringType
}